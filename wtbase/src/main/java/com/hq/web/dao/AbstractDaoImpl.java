package com.hq.web.dao;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.MappedSuperclass;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.hq.base.constant.Reg;
import com.hq.base.util.TypeUtil;

@SuppressWarnings(value = "all")
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	private String whereClauseName = "";

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public void delete(T obj) throws Exception {
		getSession().delete(obj);
		getSession().flush();
	}

	@Transactional
	@Override
	public void delete(long id) throws Exception {
		String hql = "delete from " + getEntityClass().getName() + " where id = ?";
		Query query = getSession().createQuery(hql);
		query.setLong(0, id);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void delete(String where) throws Exception {
		String hql = "delete from " + getEntityClass().getName() + " where " + where;
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings(value = "all")
	@Override
	public long findCount() throws Exception {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}

	@SuppressWarnings(value = "all")
	@Override
	public T find(long id) throws Exception {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings(value = "all")
	@Override
	public T findUnique(String columnName, Object columnValue) throws Exception {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(columnName, columnValue));
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings(value = "all")
	@Override
	public List<T> findList(String columnName, Object columnValue) throws Exception {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq(columnName, columnValue));
		return (List<T>) criteria.list();
	}

	@SuppressWarnings(value = "all")
	@Override
	public List<T> findAll() throws Exception {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		// 查询缓存
		criteria.setCacheable(true);
		return criteria.list();
	}

	@Transactional
	public void saveOrUpdate(List<T> objs) throws Exception {
		// getSession().beginTransaction();
		try {
			for (int i = 0; i < objs.size(); i++) {
				getSession().saveOrUpdate(objs.get(i));
				if (i % 200 == 0) {
					getSession().flush();
					getSession().clear();
				}
			}
			// getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
		} finally {
		}
	}

	@Transactional
	public void saveOrUpdate(T obj) throws Exception {
		getSession().saveOrUpdate(obj);
		getSession().flush();
	}

	@Override
	public int findCountByCondition(List<WtCondition> conditions) {
		Criteria c = getSession().createCriteria(getEntityClass());
		handleCriteria(conditions, c);
		c.setProjection(Projections.rowCount());
		Object lengthObj = c.uniqueResult();
		return TypeUtil.toInt(lengthObj);
	}

	@Override
	public List<T> findByCondition(int pageSize, List<WtCondition> conditions, int page) {
		Criteria c = getSession().createCriteria(getEntityClass());
		handleCriteria(conditions, c);
		if (pageSize > 0) {
			c.setMaxResults(pageSize);
		}
		if (page > 0) {
			c.setFirstResult((page - 1) * pageSize);
		}
		return c.list();
	}

	@Override
	public List<T> findByCondition(int page, int pageSize, List<WtCondition> conditions,
			Map<String, List<WtCondition>> mapTableConditions) {
		Criteria c = getSession().createCriteria(getEntityClass());
		handleCriteria(conditions, mapTableConditions, c);
		if (pageSize > 0) {
			c.setMaxResults(pageSize);
		}
		if (page > 0) {
			c.setFirstResult((page - 1) * pageSize);
		}
		return c.list();
	}

	@Override
	public List<T> findByCondition(List<WtCondition> conditions) {
		return findByCondition(0, conditions, 0);
	}

	@Override
	public T findByConditionUnique(List<WtCondition> conditions) {
		Criteria c = getSession().createCriteria(getEntityClass());
		handleCriteria(conditions, c);
		return (T) c.uniqueResult();
	}

	protected void handleCriteria(List<WtCondition> conditions, Criteria c) {
		if (conditions != null) {
			for (int i = 0; i < conditions.size(); i++) {
				WtCondition cdt = conditions.get(i);
				String mode = cdt.conditionMode;
				String fieldName = cdt.fieldName;
				Object valueStart = cdt.valueStart;
				Object valueOver = cdt.valueOver;
				Object value = cdt.fieldValue;
				List<Object> values = cdt.fieldValues;
				List<WtCondition> listForOr = cdt.listForOr;
				if (mode.equals("desc")) {
					c.addOrder(Order.desc(fieldName));
				} else if (mode.equals("asc")) {
					c.addOrder(Order.asc(fieldName));
				} else if (mode.equals("eq")) {
					c.add(Restrictions.eq(fieldName, value));
				} else if (mode.equals("ne")) {
					c.add(Restrictions.ne(fieldName, value));
				} else if (mode.equals("gt")) {
					c.add(Restrictions.gt(fieldName, value));
				} else if (mode.equals("ge")) {
					c.add(Restrictions.ge(fieldName, value));
				} else if (mode.equals("lt")) {
					c.add(Restrictions.lt(fieldName, value));
				} else if (mode.equals("le")) {
					c.add(Restrictions.le(fieldName, value));
				} else if (mode.equals("in")) {
					c.add(Restrictions.in(fieldName, values));
				} else if (mode.equals("notin")) {
					c.add(Restrictions.not(Restrictions.in(fieldName, values)));
				} else if (mode.equals("like")) {
					c.add(Restrictions.like(fieldName, value.toString(), MatchMode.ANYWHERE));
				} else if (mode.equals("between")) {
					c.add(Restrictions.between(fieldName, valueStart, valueOver));
				} else if (mode.equals("or")) {
					Disjunction criterionOr = Restrictions.or();
					for (int j = 0; j < listForOr.size(); j++) {
						WtCondition cdtOr = listForOr.get(j);
						String modeOr = cdtOr.conditionMode;
						String fieldNameOr = cdtOr.fieldName;
						Object valueStartOr = cdtOr.valueStart;
						Object valueOverOr = cdtOr.valueOver;
						Object valueOr = cdtOr.fieldValue;
						List<Object> valuesOr = cdtOr.fieldValues;
						List<WtCondition> listForOrOr = cdtOr.listForOr;
						if (modeOr.equals("eq")) {
							criterionOr.add(Restrictions.eq(fieldNameOr, valueOr));
						} else if (modeOr.equals("ne")) {
							criterionOr.add(Restrictions.ne(fieldNameOr, valueOr));
						} else if (modeOr.equals("gt")) {
							criterionOr.add(Restrictions.gt(fieldNameOr, valueOr));
						} else if (modeOr.equals("ge")) {
							criterionOr.add(Restrictions.ge(fieldNameOr, valueOr));
						} else if (modeOr.equals("lt")) {
							criterionOr.add(Restrictions.lt(fieldNameOr, valueOr));
						} else if (modeOr.equals("le")) {
							criterionOr.add(Restrictions.le(fieldNameOr, valueOr));
						} else if (modeOr.equals("in")) {
							criterionOr.add(Restrictions.in(fieldNameOr, valuesOr));
						} else if (modeOr.equals("notin")) {
							criterionOr.add(Restrictions.not(Restrictions.in(fieldNameOr, valuesOr)));
						} else if (modeOr.equals("like")) {
							criterionOr.add(Restrictions.like(fieldNameOr, valueOr.toString(), MatchMode.ANYWHERE));
						} else if (modeOr.equals("between")) {
							criterionOr.add(Restrictions.between(fieldNameOr, valueStartOr, valueOverOr));
						}
					}
					c.add(criterionOr);
				}
			}
		}
	}

	protected void handleCriteria(List<WtCondition> conditions, Map<String, List<WtCondition>> mapTableConditions,
			Criteria c) {
		handleCriteria(conditions, c);
		if (mapTableConditions != null) {
			String alias = "a";
			int index = 1;
			for (Map.Entry<String, List<WtCondition>> entry : mapTableConditions.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				Criteria cJoin = c.createAlias(entry.getKey(), alias + index);
				handleCriteria(entry.getValue(), cJoin);
			}
		}
	}

	@Override
	public Object executeHql(String hql) throws Exception {
		Query query = getSession().createQuery(hql);
		return query.uniqueResult();
	}

	@Override
	public Object attachObject(Object o) throws Exception {
		return getSession().merge(o);
	}

	@Override
	public void refresh(Object o) throws Exception {
		getSession().refresh(o);
	}

	public Class getReturnClass() {
		return null;
	}

	private static String getGetMethod(Field field) {
		if (field != null) {
			// 如果是boolean型的，则以is开始，不然以get开头
			String getStr = (field.getType() == boolean.class ? "is" : "get");
			if (field.getName().length() == 1) {
				return getStr + field.getName().toUpperCase();
			} else if (Pattern.matches(Reg.SECONDUPPER, field.getName())) {
				if (getStr.equals("is") && field.getName().length() > 2
						&& field.getName().substring(0, 2).toLowerCase().equals("is")) {
					return getStr + field.getName();
				} else {
					return getStr + field.getName();
				}
			} else {
				if (getStr.equals("is") && field.getName().length() > 2
						&& field.getName().substring(0, 2).toLowerCase().equals("is")) {
					return getStr + field.getName().substring(2, 3).toUpperCase() + field.getName().substring(3);
				} else {
					return getStr + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
				}
			}
		} else {
			return null;
		}
	}

	public static Class<?> findLastSuperClass(Class<?> clazz) {
		Class<?> superClass = clazz.getSuperclass();
		MappedSuperclass mappedClass;
		while (superClass != null && superClass != Number.class && superClass != Object.class) {
			mappedClass = superClass.getAnnotation(MappedSuperclass.class);
			if (mappedClass != null) {
				return superClass;
			} else {
				superClass = superClass.getSuperclass();
			}
		}
		return null;
	}

}

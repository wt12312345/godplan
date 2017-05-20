package com.godplan.constant;

public enum EnumCom {
	Creating {
		public String getName() {
			return "创建中";
		}

		public int getIndex() {
			return 0;
		}
	},
	/**
	 * 删除
	 * 
	 * @author Administrator
	 * 
	 */
	DELETE {
		public String getName() {
			return "已删除";
		}

		public int getIndex() {
			return -1;
		}
	},
	/**
	 * 作废/关闭等
	 */
	Invalid {
		public String getName() {
			return "已作废";
		}

		public int getIndex() {
			return -2;
		}
	},
	/**
	 * 作废/关闭等
	 */
	Close {
		public String getName() {
			return "已关闭";
		}

		public int getIndex() {
			return -2;
		}
	},
	/**
	 * 冻结
	 * 
	 * @author Administrator
	 * 
	 */
	Freeze {
		public String getName() {
			return "冻结";
		}

		public int getIndex() {
			return 88;
		}
	},
	/**
	 * 启用
	 * 
	 * @author Administrator
	 * 
	 */
	Enable {
		public String getName() {
			return "启用";
		}

		public int getIndex() {
			return 1;
		}
	},
	SexNo {
		public String getName() {
			return "未知";
		}

		public int getIndex() {
			return 0;
		}
	},
	SexMan {
		public String getName() {
			return "男";
		}

		public int getIndex() {
			return 1;
		}

	},
	SexWoman {
		public String getName() {
			return "女";
		}

		public int getIndex() {
			return 2;
		}
	},
	CurrencyRmb {
		public String getName() {
			return "人民币";
		}

		public int getIndex() {
			return 1;
		}
	},
	CurrencyDollar {
		public String getName() {
			return "美元";
		}

		public int getIndex() {
			return 2;
		}
	},
	CurrencyEuro {
		public String getName() {
			return "欧元";
		}

		public int getIndex() {
			return 3;
		}
	},
	CurrencyYen {
		public String getName() {
			return "日元";
		}

		public int getIndex() {
			return 4;
		}
	},
	CurrencyRmbSign {
		public String getName() {
			return "￥";
		}

		public int getIndex() {
			return 1;
		}
	},
	CurrencyDollarSign {
		public String getName() {
			return "$";
		}

		public int getIndex() {
			return 2;
		}
	},
	CurrencyEuroSign {
		public String getName() {
			return "€";
		}

		public int getIndex() {
			return 3;
		}
	},
	CurrencyYenSign {
		public String getName() {
			return "円";
		}

		public int getIndex() {
			return 4;
		}
	};

	public abstract String getName();

	public abstract int getIndex();
}

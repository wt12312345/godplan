/**
 * TODO
 */
(function($, undefined) {

	function testProps(props) {
		var i;
		for (i in props) {
			if (mod[props[i]] !== undefined) {
				return true;
			}
		}
		return false;
	}

	function testPrefix() {
		var prefixes = ['Webkit', 'Moz', 'O', 'ms'],
			p;

		for (p in prefixes) {
			if (testProps([prefixes[p] + 'Transform'])) {
				return '-' + prefixes[p].toLowerCase() + '-';
			}
		}
		return '';
	}

	function init(that, options, args) {
		var ret = that;

		// Init
		if (typeof options === 'object') {
			return that.each(function() {
				if (instances[this.id]) {
					instances[this.id].destroy();
				}
				new $.mobiscroll.classes[options.component || 'Scroller'](this, options);
			});
		}

		// Method call
		if (typeof options === 'string') {
			that.each(function() {
				var r,
					inst = instances[this.id];

				if (inst && inst[options]) {
					r = inst[options].apply(this, Array.prototype.slice.call(args, 1));
					if (r !== undefined) {
						ret = r;
						return false;
					}
				}
			});
		}

		return ret;
	}

	var id = +new Date(),
		instances = {},
		extend = $.extend,
		mod = document.createElement('modernizr').style,
		has3d = testProps(['perspectiveProperty', 'WebkitPerspective', 'MozPerspective', 'OPerspective', 'msPerspective']),
		hasFlex = testProps(['flex', 'msFlex', 'WebkitBoxDirection']),
		prefix = testPrefix(),
		pr = prefix.replace(/^\-/, '').replace(/\-$/, '').replace('moz', 'Moz');

	$.fn.mobiscroll = function(method) {
		extend(this, $.mobiscroll.components);
		return init(this, method, arguments);
	};

	$.mobiscroll = $.mobiscroll || {
		version: '2.15.1',
		util: {
			prefix: prefix,
			jsPrefix: pr,
			has3d: has3d,
			hasFlex: hasFlex,
			testTouch: function(e, elm) {
				if (e.type == 'touchstart') {
					$(elm).attr('data-touch', '1');
				} else if ($(elm).attr('data-touch')) {
					$(elm).removeAttr('data-touch');
					return false;
				}
				return true;
			},
			objectToArray: function(obj) {
				var arr = [],
					i;

				for (i in obj) {
					arr.push(obj[i]);
				}

				return arr;
			},
			arrayToObject: function(arr) {
				var obj = {},
					i;

				if (arr) {
					for (i = 0; i < arr.length; i++) {
						obj[arr[i]] = arr[i];
					}
				}

				return obj;
			},
			isNumeric: function(a) {
				return a - parseFloat(a) >= 0;
			},
			isString: function(s) {
				return typeof s === 'string';
			},
			getCoord: function(e, c) {
				var ev = e.originalEvent || e;
				return ev.changedTouches ? ev.changedTouches[0]['page' + c] : e['page' + c];
			},
			getPosition: function(t, vertical) {
				var style = window.getComputedStyle ? getComputedStyle(t[0]) : t[0].style,
					matrix,
					px;

				if (has3d) {
					$.each(['t', 'webkitT', 'MozT', 'OT', 'msT'], function(i, v) {
						if (style[v + 'ransform'] !== undefined) {
							matrix = style[v + 'ransform'];
							return false;
						}
					});
					matrix = matrix.split(')')[0].split(', ');
					px = vertical ? (matrix[13] || matrix[5]) : (matrix[12] || matrix[4]);
				} else {
					px = vertical ? style.top.replace('px', '') : style.left.replace('px', '');
				}

				return px;
			},
			constrain: function(val, min, max) {
				return Math.max(min, Math.min(val, max));
			},
			vibrate: function(time) {
				if ('vibrate' in navigator) {
					navigator.vibrate(time || 50);
				}
			}
		},
		tapped: false,
		autoTheme: 'mobiscroll',
		presets: {
			scroller: {},
			numpad: {},
			listview: {},
			menustrip: {}
		},
		themes: {
			frame: {},
			listview: {},
			menustrip: {}
		},
		i18n: {},
		instances: instances,
		classes: {},
		components: {},
		defaults: {
			context: 'body',
			mousewheel: true,
			vibrate: true
		},
		setDefaults: function(o) {
			extend(this.defaults, o);
		},
		presetShort: function(name, c, p) {
			this.components[name] = function(s) {
				return init(this, extend(s, {
					component: c,
					preset: p === false ? undefined : name
				}), arguments);
			};
		}
	};

	$.mobiscroll.classes.Base = function(el, settings) {

		var lang,
			preset,
			s,
			theme,
			themeName,
			defaults,
			ms = $.mobiscroll,
			that = this;

		that.settings = {};

		that._presetLoad = function() {};

		that._init = function(ss) {
			s = that.settings;

			// Update original user settings
			extend(settings, ss);

			// Load user defaults
			if (that._hasDef) {
				defaults = ms.defaults;
			}

			// Create settings object
			extend(s, that._defaults, defaults, settings);

			// Get theme defaults
			if (that._hasTheme) {

				themeName = s.theme;

				if (themeName == 'auto' || !themeName) {
					themeName = ms.autoTheme;
				}

				if (themeName == 'default') {
					themeName = 'mobiscroll';
				}

				settings.theme = themeName;

				theme = ms.themes[that._class][themeName];
			}

			// Get language defaults
			if (that._hasLang) {
				lang = ms.i18n[s.lang];
			}

			if (that._hasTheme) {
				that.trigger('onThemeLoad', [lang, settings]);
			}

			// Update settings object
			extend(s, theme, lang, defaults, settings);

			// Load preset settings
			if (that._hasPreset) {

				that._presetLoad(s);

				preset = ms.presets[that._class][s.preset];

				if (preset) {
					preset = preset.call(el, that);
					extend(s, preset, settings);
				}
			}
		};

		that._destroy = function() {
			that.trigger('onDestroy', []);

			// Delete scroller instance
			delete instances[el.id];

			that = null;
		};

		/**
		 * Triggers an event
		 */
		that.trigger = function(name, args) {
			var ret;
			args.push(that);
			$.each([defaults, theme, preset, settings], function(i, v) {
				if (v && v[name]) { // Call preset event
					ret = v[name].apply(el, args);
				}
			});
			return ret;
		};

		/**
		 * Sets one ore more options.
		 */
		that.option = function(opt, value) {
			var obj = {};
			if (typeof opt === 'object') {
				obj = opt;
			} else {
				obj[opt] = value;
			}
			that.init(obj);
		};

		/**
		 * Returns the mobiscroll instance.
		 */
		that.getInst = function() {
			return that;
		};

		settings = settings || {};

		// Autogenerate id
		if (!el.id) {
			el.id = 'mobiscroll' + (++id);
		}

		// Save instance
		instances[el.id] = that;
	};

})(jQuery);

(function($, window, document, undefined) {

	var $activeElm,
		preventShow,
		ms = $.mobiscroll,
		instances = ms.instances,
		util = ms.util,
		pr = util.jsPrefix,
		has3d = util.has3d,
		getCoord = util.getCoord,
		constrain = util.constrain,
		isString = util.isString,
		isOldAndroid = /android [1-3]/i.test(navigator.userAgent),
		isIOS8 = /(iphone|ipod|ipad).* os 8_/i.test(navigator.userAgent),
		animEnd = 'webkitAnimationEnd animationend',
		empty = function() {},
		prevdef = function(ev) {
			ev.preventDefault();
		};

	ms.classes.Frame = function(el, settings, inherit) {
		var $ariaDiv,
			$ctx,
			$header,
			$markup,
			$overlay,
			$persp,
			$popup,
			$wnd,
			$wrapper,
			buttons,
			btn,
			doAnim,
			event,
			hasButtons,
			isModal,
			modalWidth,
			modalHeight,
			posEvents,
			preventPos,
			s,
			scrollLock,
			setReadOnly,
			wndWidth,
			wndHeight,

			that = this,
			$elm = $(el),
			elmList = [],
			posDebounce = {};

		function onBtnStart(ev) {
			// Can't call preventDefault here, it kills page scroll
			if (btn) {
				btn.removeClass('dwb-a');
			}
			btn = $(this);
			// Active button
			if (!btn.hasClass('dwb-d') && !btn.hasClass('dwb-nhl')) {
				btn.addClass('dwb-a');
			}
			if (ev.type === 'mousedown') {
				$(document).on('mouseup', onBtnEnd);
			}
		}

		function onBtnEnd(ev) {
			if (btn) {
				btn.removeClass('dwb-a');
				btn = null;
			}
			if (ev.type === 'mouseup') {
				$(document).off('mouseup', onBtnEnd);
			}
		}

		function onWndKeyDown(ev) {
			if (ev.keyCode == 13) {
				that.select();
			} else if (ev.keyCode == 27) {
				that.cancel();
			}
		}

		function onShow(prevFocus) {
			if (!prevFocus) {
				$popup.focus();
			}
			that.ariaMessage(s.ariaMessage);
		}

		function onHide(prevAnim) {
			var activeEl,
				value,
				type,
				focus = s.focusOnClose;

			$markup.remove();

			if ($activeElm && !prevAnim) {
				setTimeout(function() {
					if (focus === undefined || focus === true) {
						preventShow = true;
						activeEl = $activeElm[0];
						type = activeEl.type;
						value = activeEl.value;
						try {
							activeEl.type = 'button';
						} catch (ex) {}
						$activeElm.focus();
						activeEl.type = type;
						activeEl.value = value;
					} else if (focus) {
						// If a mobiscroll field is focused, allow show
						if (instances[$(focus).attr('id')]) {
							ms.tapped = false;
						}
						$(focus).focus();
					}
				}, 200);
			}

			that._isVisible = false;

			event('onHide', []);
		}

		function onPosition(ev) {
			clearTimeout(posDebounce[ev.type]);
			posDebounce[ev.type] = setTimeout(function() {
				var isScroll = ev.type == 'scroll';
				if (isScroll && !scrollLock) {
					return;
				}
				that.position(!isScroll);
			}, 200);
		}

		function onFocus(ev) {
			if (!$popup[0].contains(ev.target)) {
				$popup.focus();
			}
		}

		function show(beforeShow, $elm) {
			if (!ms.tapped) {

				if (beforeShow) {
					beforeShow();
				}

				// Hide virtual keyboard
				if ($(document.activeElement).is('input,textarea')) {
					$(document.activeElement).blur();
				}

				$activeElm = $elm;
				that.show();
			}

			setTimeout(function() {
				preventShow = false;
			}, 300); // With jQuery < 1.9 focus is fired twice in IE
		}

		// Call the parent constructor
		ms.classes.Base.call(this, el, settings, true);

		/**
		 * Positions the scroller on the screen.
		 */
		that.position = function(check) {
			var w,
				l,
				t,
				anchor,
				aw, // anchor width
				ah, // anchor height
				ap, // anchor position
				at, // anchor top
				al, // anchor left
				arr, // arrow
				arrw, // arrow width
				arrl, // arrow left
				dh,
				scroll,
				sl, // scroll left
				st, // scroll top
				totalw = 0,
				minw = 0,
				css = {},
				nw = Math.min($wnd[0].innerWidth || $wnd.innerWidth(), $persp.width()), //$persp.width(), // To get the width without scrollbar
				nh = $wnd[0].innerHeight || $wnd.innerHeight();

			if ((wndWidth === nw && wndHeight === nh && check) || preventPos) {
				return;
			}

			if (that._isFullScreen || /top|bottom/.test(s.display)) {
				// Set width, if document is larger than viewport, needs to be set before onPosition (for calendar)
				$popup.width(nw);
			}

			if (event('onPosition', [$markup, nw, nh]) === false || !isModal) {
				return;
			}

			sl = $wnd.scrollLeft();
			st = $wnd.scrollTop();
			anchor = s.anchor === undefined ? $elm : $(s.anchor);

			// Set / unset liquid layout based on screen width, but only if not set explicitly by the user
			if (that._isLiquid && s.layout !== 'liquid') {
				if (nw < 400) {
					$markup.addClass('dw-liq');
				} else {
					$markup.removeClass('dw-liq');
				}
			}

			if (!that._isFullScreen && /modal|bubble/.test(s.display)) {
				$wrapper.width('');
				$('.mbsc-w-p', $markup).each(function() {
					w = $(this).outerWidth(true);
					totalw += w;
					minw = (w > minw) ? w : minw;
				});
				w = totalw > nw ? minw : totalw;
				$wrapper.width(w).css('white-space', totalw > nw ? '' : 'nowrap');
			}

			modalWidth = that._isFullScreen ? nw : $popup.outerWidth();
			modalHeight = that._isFullScreen ? nh : $popup.outerHeight(true);
			scrollLock = modalHeight <= nh && modalWidth <= nw;

			that.scrollLock = scrollLock;

			if (s.display == 'modal') {
				l = Math.max(0, sl + (nw - modalWidth) / 2);
				t = st + (nh - modalHeight) / 2;
			} else if (s.display == 'bubble') {
				scroll = true;
				arr = $('.dw-arrw-i', $markup);
				ap = anchor.offset();
				at = Math.abs($ctx.offset().top - ap.top);
				al = Math.abs($ctx.offset().left - ap.left);

				// horizontal positioning
				aw = anchor.outerWidth();
				ah = anchor.outerHeight();
				l = constrain(al - ($popup.outerWidth(true) - aw) / 2, sl + 3, sl + nw - modalWidth - 3);

				// vertical positioning
				t = at - modalHeight; // above the input
				if ((t < st) || (at > st + nh)) { // if doesn't fit above or the input is out of the screen
					$popup.removeClass('dw-bubble-top').addClass('dw-bubble-bottom');
					t = at + ah; // below the input
				} else {
					$popup.removeClass('dw-bubble-bottom').addClass('dw-bubble-top');
				}

				// Calculate Arrow position
				arrw = arr.outerWidth();
				arrl = constrain(al + aw / 2 - (l + (modalWidth - arrw) / 2), 0, arrw);

				// Limit Arrow position
				$('.dw-arr', $markup).css({
					left: arrl
				});
			} else {
				l = sl;
				if (s.display == 'top') {
					t = st;
				} else if (s.display == 'bottom') {
					t = st + nh - modalHeight;
				}
			}

			t = t < 0 ? 0 : t;

			css.top = t;
			css.left = l;
			$popup.css(css);

			// If top + modal height > doc height, increase doc height
			$persp.height(0);
			dh = Math.max(t + modalHeight, s.context == 'body' ? $(document).height() : $ctx[0].scrollHeight);
			$persp.css({
				height: dh
			});

			// Scroll needed
			if (scroll && ((t + modalHeight > st + nh) || (at > st + nh))) {
				preventPos = true;
				setTimeout(function() {
					preventPos = false;
				}, 300);
				$wnd.scrollTop(Math.min(t + modalHeight - nh, dh - nh));
			}

			wndWidth = nw;
			wndHeight = nh;
		};

		/**
		 * Show mobiscroll on focus and click event of the parameter.
		 * @param {jQuery} $elm - Events will be attached to this element.
		 * @param {Function} [beforeShow=undefined] - Optional function to execute before showing mobiscroll.
		 */
		that.attachShow = function($elm, beforeShow) {
			elmList.push({
				readOnly: $elm.prop('readonly'),
				el: $elm
			});
			if (s.display !== 'inline') {
				if (setReadOnly && $elm.is('input')) {
					$elm.prop('readonly', true).on('mousedown.dw', function(ev) {
						// Prevent input to get focus on tap (virtual keyboard pops up on some devices)
						ev.preventDefault();
					});
				}

				if (s.showOnFocus) {
					$elm.on('focus.dw', function() {
						if (!preventShow) {
							show(beforeShow, $elm);
						}
					});
				}

				if (s.showOnTap) {

					$elm.on('keydown.dw', function(ev) {
						if (ev.keyCode == 32 || ev.keyCode == 13) { // Space or Enter
							ev.preventDefault();
							ev.stopPropagation();
							show(beforeShow, $elm);
						}
					});

					that.tap($elm, function() {
						show(beforeShow, $elm);
					});
				}
			}
		};

		/**
		 * Set button handler.
		 */
		that.select = function() {
			if (!isModal || that.hide(false, 'set') !== false) {
				that._fillValue();
				event('onSelect', [that._value]);
			}
		};

		/**
		 * Cancel and hide the scroller instance.
		 */
		that.cancel = function() {
			if (!isModal || that.hide(false, 'cancel') !== false) {
				event('onCancel', [that._value]);
			}
		};

		/**
		 * Clear button handler.
		 */
		that.clear = function() {
			event('onClear', [$markup]);
			if (isModal && !that.live) {
				that.hide(false, 'clear');
			}
			that.setVal(null, true);
		};

		/**
		 * Enables the scroller and the associated input.
		 */
		that.enable = function() {
			s.disabled = false;
			if (that._isInput) {
				$elm.prop('disabled', false);
			}
		};

		/**
		 * Disables the scroller and the associated input.
		 */
		that.disable = function() {
			s.disabled = true;
			if (that._isInput) {
				$elm.prop('disabled', true);
			}
		};

		/**
		 * Shows the scroller instance.
		 * @param {Boolean} prevAnim - Prevent animation if true
		 * @param {Boolean} prevFocus - Prevent focusing if true
		 */
		that.show = function(prevAnim, prevFocus) {
			// Create wheels
			var html;

			if (s.disabled || that._isVisible) {
				return;
			}

			if (doAnim !== false) {
				if (s.display == 'top') {
					doAnim = 'slidedown';
				}
				if (s.display == 'bottom') {
					doAnim = 'slideup';
				}
			}

			// Parse value from input
			that._readValue();

			event('onBeforeShow', []);

			// Create wheels containers
			html = '<div lang="' + s.lang + '" class="mbsc-' + s.theme + (s.baseTheme ? ' mbsc-' + s.baseTheme : '') + ' dw-' + s.display + ' ' +
				(s.cssClass || '') +
				(that._isLiquid ? ' dw-liq' : '') +
				(isOldAndroid ? ' mbsc-old' : '') +
				(hasButtons ? '' : ' dw-nobtn') + '">' +
				'<div class="dw-persp">' +
				(isModal ? '<div class="dwo"></div>' : '') + // Overlay
				'<div' + (isModal ? ' role="dialog" tabindex="-1"' : '') + ' class="dw' + (s.rtl ? ' dw-rtl' : ' dw-ltr') + '">' + // Popup
				(s.display === 'bubble' ? '<div class="dw-arrw"><div class="dw-arrw-i"><div class="dw-arr"></div></div></div>' : '') + // Bubble arrow
				'<div class="dwwr">' + // Popup content
				'<div aria-live="assertive" class="dw-aria dw-hidden"></div>' +
				(s.headerText ? '<div class="dwv">' + (isString(s.headerText) ? s.headerText : '') + '</div>' : '') + // Header
				'<div class="dwcc">'; // Wheel group container

			html += that._generateContent();

			html += '</div>';

			if (hasButtons) {
				html += '<div class="dwbc">';
				$.each(buttons, function(i, b) {
					b = isString(b) ? that.buttons[b] : b;

					if (b.handler === 'set') {
						b.parentClass = 'dwb-s';
					}

					if (b.handler === 'cancel') {
						b.parentClass = 'dwb-c';
					}

					b.handler = isString(b.handler) ? that.handlers[b.handler] : b.handler;

					html += '<div' + (s.btnWidth ? ' style="width:' + (100 / buttons.length) + '%"' : '') + ' class="dwbw ' + (b.parentClass || '') + '"><div tabindex="0" role="button" class="dwb' + i + ' dwb-e ' + (b.cssClass === undefined ? s.btnClass : b.cssClass) + (b.icon ? ' mbsc-ic mbsc-ic-' + b.icon : '') + '">' + (b.text || '') + '</div></div>';
				});
				html += '</div>';
			}
			html += '</div></div></div></div>';

			$markup = $(html);
			$persp = $('.dw-persp', $markup);
			$overlay = $('.dwo', $markup);
			$wrapper = $('.dwwr', $markup);
			$header = $('.dwv', $markup);
			$popup = $('.dw', $markup);
			$ariaDiv = $('.dw-aria', $markup);

			that._markup = $markup;
			that._header = $header;
			that._isVisible = true;

			posEvents = 'orientationchange resize';

			that._markupReady($markup);

			event('onMarkupReady', [$markup]);

			// Show
			if (isModal) {

				// Enter / ESC
				$(window).on('keydown', onWndKeyDown);

				// Prevent scroll if not specified otherwise
				if (s.scrollLock) {
					$markup.on('touchmove mousewheel wheel', function(ev) {
						if (scrollLock) {
							ev.preventDefault();
						}
					});
				}

				// Disable inputs to prevent bleed through (Android bug)
				if (pr !== 'Moz') {
					$('input,select,button', $ctx).each(function() {
						if (!this.disabled) {
							$(this).addClass('dwtd').prop('disabled', true);
						}
					});
				}

				posEvents += ' scroll';

				ms.activeInstance = that;

				$markup.appendTo($ctx);

				if (has3d && doAnim && !prevAnim) {
					$markup.addClass('dw-in dw-trans').on(animEnd, function() {
						$markup.off(animEnd).removeClass('dw-in dw-trans').find('.dw').removeClass('dw-' + doAnim);
						onShow(prevFocus);
					}).find('.dw').addClass('dw-' + doAnim);
				}
			} else if ($elm.is('div') && !that._hasContent) {
				$elm.html($markup);
			} else {
				$markup.insertAfter($elm);
			}

			event('onMarkupInserted', [$markup]);

			// Set position
			that.position();

			$wnd
				.on(posEvents, onPosition)
				.on('focusin', onFocus);

			// Events
			$markup
				.on('selectstart mousedown', prevdef) // Prevents blue highlight on Android and text selection in IE
				.on('click', '.dwb-e', prevdef)
				.on('keydown', '.dwb-e', function(ev) {
					if (ev.keyCode == 32) { // Space
						ev.preventDefault();
						ev.stopPropagation();
						$(this).click();
					}
				})
				.on('keydown', function(ev) { // Trap focus inside modal
					if (ev.keyCode == 32) { // Space
						ev.preventDefault();
					} else if (ev.keyCode == 9) { // Tab

						var $focusable = $markup.find('[tabindex="0"]').filter(function() {
								return this.offsetWidth > 0 || this.offsetHeight > 0;
							}),
							index = $focusable.index($(':focus', $markup)),
							i = $focusable.length - 1,
							target = 0;

						if (ev.shiftKey) {
							i = 0;
							target = -1;
						}

						if (index === i) {
							$focusable.eq(target).focus();
							ev.preventDefault();
						}

					}
				});

			$('input', $markup).on('selectstart mousedown', function(ev) {
				ev.stopPropagation();
			});

			setTimeout(function() {
				// Init buttons
				$.each(buttons, function(i, b) {
					that.tap($('.dwb' + i, $markup), function(ev) {
						b = isString(b) ? that.buttons[b] : b;
						b.handler.call(this, ev, that);
					}, true);
				});

				if (s.closeOnOverlay) {
					that.tap($overlay, function() {
						that.cancel();
					});
				}

				if (isModal && !doAnim) {
					onShow(prevFocus);
				}

				$markup
					.on('touchstart mousedown', '.dwb-e', onBtnStart)
					.on('touchend', '.dwb-e', onBtnEnd);

				that._attachEvents($markup);

			}, 300);

			event('onShow', [$markup, that._tempValue]);
		};

		/**
		 * Hides the scroller instance.
		 */
		that.hide = function(prevAnim, btn, force) {

			// If onClose handler returns false, prevent hide
			if (!that._isVisible || (!force && !that._isValid && btn == 'set') || (!force && event('onClose', [that._tempValue, btn]) === false)) {
				return false;
			}

			// Hide wheels and overlay
			if ($markup) {

				// Re-enable temporary disabled fields
				if (pr !== 'Moz') {
					$('.dwtd', $ctx).each(function() {
						$(this).prop('disabled', false).removeClass('dwtd');
					});
				}

				if (has3d && isModal && doAnim && !prevAnim && !$markup.hasClass('dw-trans')) { // If dw-trans class was not removed, means that there was no animation
					$markup.addClass('dw-out dw-trans').find('.dw').addClass('dw-' + doAnim).on(animEnd, function() {
						onHide(prevAnim);
					});
				} else {
					onHide(prevAnim);
				}

				// Stop positioning on window resize
				$wnd
					.off(posEvents, onPosition)
					.off('focusin', onFocus);
			}

			if (isModal) {
				$(window).off('keydown', onWndKeyDown);
				delete ms.activeInstance;
			}
		};

		that.ariaMessage = function(txt) {
			$ariaDiv.html('');
			setTimeout(function() {
				$ariaDiv.html(txt);
			}, 100);
		};

		/**
		 * Return true if the scroller is currently visible.
		 */
		that.isVisible = function() {
			return that._isVisible;
		};

		// Protected functions to override

		that.setVal = empty;

		that._generateContent = empty;

		that._attachEvents = empty;

		that._readValue = empty;

		that._fillValue = empty;

		that._markupReady = empty;

		that._processSettings = empty;

		that._presetLoad = function(s) {
			// Add default buttons
			s.buttons = s.buttons || (s.display !== 'inline' ? ['set', 'cancel'] : []);

			// Hide header text in inline mode by default
			s.headerText = s.headerText === undefined ? (s.display !== 'inline' ? '{value}' : false) : s.headerText;
		};

		// Generic frame functions

		/**
		 * Attach tap event to the given element.
		 */
		that.tap = function(el, handler, prevent) {
			var startX,
				startY,
				moved;

			if (s.tap) {
				el.on('touchstart.dw', function(ev) {
					// Can't always call preventDefault here, it kills page scroll
					if (prevent) {
						ev.preventDefault();
					}
					startX = getCoord(ev, 'X');
					startY = getCoord(ev, 'Y');
					moved = false;
				}).on('touchmove.dw', function(ev) {
					// If movement is more than 20px, don't fire the click event handler
					if (Math.abs(getCoord(ev, 'X') - startX) > 20 || Math.abs(getCoord(ev, 'Y') - startY) > 20) {
						moved = true;
					}
				}).on('touchend.dw', function(ev) {
					var that = this;

					if (!moved) {
						// preventDefault and setTimeout are needed by iOS
						ev.preventDefault();
						//setTimeout(function () {
						handler.call(that, ev);
						//}, isOldAndroid ? 400 : 10);
					}
					// Prevent click events to happen
					ms.tapped = true;
					setTimeout(function() {
						ms.tapped = false;
					}, 500);
				});
			}

			el.on('click.dw', function(ev) {
				if (!ms.tapped) {
					// If handler was not called on touchend, call it on click;
					handler.call(this, ev);
				}
				ev.preventDefault();
			});

		};

		/**
		 * Destroys the mobiscroll instance.
		 */
		that.destroy = function() {
			// Force hide without animation
			that.hide(true, false, true);

			// Remove all events from elements
			$.each(elmList, function(i, v) {
				v.el.off('.dw').prop('readonly', v.readOnly);
			});

			that._destroy();
		};

		/**
		 * Scroller initialization.
		 */
		that.init = function(ss) {

			that._init(ss);

			that._isLiquid = (s.layout || (/top|bottom/.test(s.display) ? 'liquid' : '')) === 'liquid';

			that._processSettings();

			// Unbind all events (if re-init)
			$elm.off('.dw');

			doAnim = isOldAndroid ? false : s.animate;
			buttons = s.buttons || [];
			isModal = s.display !== 'inline';
			setReadOnly = s.showOnFocus || s.showOnTap;
			$wnd = $(s.context == 'body' ? window : s.context);
			$ctx = $(s.context);

			that.context = $wnd;

			that.live = true;

			// If no set button is found, live mode is activated
			$.each(buttons, function(i, b) {
				if (b == 'ok' || b == 'set' || b.handler == 'set') {
					that.live = false;
					return false;
				}
			});

			that.buttons.set = {
				text: s.setText,
				handler: 'set'
			};
			that.buttons.cancel = {
				text: (that.live) ? s.closeText : s.cancelText,
				handler: 'cancel'
			};
			that.buttons.clear = {
				text: s.clearText,
				handler: 'clear'
			};

			that._isInput = $elm.is('input');

			hasButtons = buttons.length > 0;

			if (that._isVisible) {
				that.hide(true, false, true);
			}

			event('onInit', []);

			if (isModal) {
				that._readValue();
				if (!that._hasContent) {
					that.attachShow($elm);
				}
			} else {
				that.show();
			}

			$elm.on('change.dw', function() {
				if (!that._preventChange) {
					that.setVal($elm.val(), true, false);
				}
				that._preventChange = false;
			});
		};

		that.buttons = {};
		that.handlers = {
			set: that.select,
			cancel: that.cancel,
			clear: that.clear
		};

		that._value = null;

		that._isValid = true;
		that._isVisible = false;

		// Constructor

		s = that.settings;
		event = that.trigger;

		if (!inherit) {
			that.init(settings);
		}
	};

	ms.classes.Frame.prototype._defaults = {
		// Localization
		lang: 'en',
		setText: 'Set',
		selectedText: 'Selected',
		closeText: 'Close',
		cancelText: 'Cancel',
		clearText: 'Clear',
		// Options
		disabled: false,
		closeOnOverlay: true,
		showOnFocus: false,
		showOnTap: true,
		display: 'modal',
		scrollLock: true,
		tap: true,
		btnClass: 'dwb',
		btnWidth: true,
		focusOnClose: !isIOS8 // Temporary for iOS8
	};

	ms.themes.frame.mobiscroll = {
		rows: 5,
		showLabel: false,
		headerText: false,
		btnWidth: false,
		selectedLineHeight: true,
		selectedLineBorder: 1,
		dateOrder: 'MMddyy',
		weekDays: 'min',
		checkIcon: 'ion-ios7-checkmark-empty',
		btnPlusClass: 'mbsc-ic mbsc-ic-arrow-down5',
		btnMinusClass: 'mbsc-ic mbsc-ic-arrow-up5',
		btnCalPrevClass: 'mbsc-ic mbsc-ic-arrow-left5',
		btnCalNextClass: 'mbsc-ic mbsc-ic-arrow-right5'
	};

	// Prevent re-show on window focus
	$(window).on('focus', function() {
		if ($activeElm) {
			preventShow = true;
		}
	});

	// Prevent standard behaviour on body click
	$(document).on('mouseover mouseup mousedown click', function(ev) {
		if (ms.tapped) {
			ev.stopPropagation();
			ev.preventDefault();
			return false;
		}
	});

})(jQuery, window, document);

(function($, window, document, undefined) {

	var move,
		ms = $.mobiscroll,
		classes = ms.classes,
		util = ms.util,
		pr = util.jsPrefix,
		has3d = util.has3d,
		hasFlex = util.hasFlex,
		getCoord = util.getCoord,
		constrain = util.constrain,
		testTouch = util.testTouch;

	ms.presetShort('scroller', 'Scroller', false);

	classes.Scroller = function(el, settings, inherit) {
		var $markup,
			btn,
			isScrollable,
			itemHeight,
			multiple,
			s,
			scrollDebounce,
			trigger,

			click,
			moved,
			start,
			startTime,
			stop,
			p,
			min,
			max,
			target,
			index,
			lines,
			timer,
			that = this,
			$elm = $(el),
			iv = {},
			pos = {},
			pixels = {},
			wheels = [];

		// Event handlers

		function onStart(ev) {
			// Scroll start
			if (testTouch(ev, this) && !move && !click && !btn && !isReadOnly(this)) {
				// Prevent touch highlight
				ev.preventDefault();
				// Better performance if there are tap events on document
				ev.stopPropagation();

				move = true;
				isScrollable = s.mode != 'clickpick';
				target = $('.dw-ul', this);
				setGlobals(target);
				moved = iv[index] !== undefined; // Don't allow tap, if still moving
				p = moved ? getCurrentPosition(target) : pos[index];
				start = getCoord(ev, 'Y');
				startTime = new Date();
				stop = start;
				scroll(target, index, p, 0.001);

				if (isScrollable) {
					target.closest('.dwwl').addClass('dwa');
				}

				if (ev.type === 'mousedown') {
					$(document).on('mousemove', onMove).on('mouseup', onEnd);
				}
			}
		}

		function onMove(ev) {
			if (move) {
				if (isScrollable) {
					// Prevent scroll
					ev.preventDefault();
					ev.stopPropagation();
					stop = getCoord(ev, 'Y');
					if (Math.abs(stop - start) > 3 || moved) {
						scroll(target, index, constrain(p + (start - stop) / itemHeight, min - 1, max + 1));
						moved = true;
					}
				}
			}
		}

		function onEnd(ev) {
			if (move) {
				var time = new Date() - startTime,
					curr = constrain(Math.round(p + (start - stop) / itemHeight), min - 1, max + 1),
					val = curr,
					speed,
					dist,
					ttop = target.offset().top;

				// Better performance if there are tap events on document
				ev.stopPropagation();

				move = false;

				if (ev.type === 'mouseup') {
					$(document).off('mousemove', onMove).off('mouseup', onEnd);
				}

				if (has3d && time < 300) {
					speed = (stop - start) / time;
					dist = (speed * speed) / s.speedUnit;
					if (stop - start < 0) {
						dist = -dist;
					}
				} else {
					dist = stop - start;
				}

				if (!moved) { // this is a "tap"
					var idx = Math.floor((stop - ttop) / itemHeight),
						li = $($('.dw-li', target)[idx]),
						valid = li.hasClass('dw-v'),
						hl = isScrollable;

					time = 0.1;

					if (trigger('onValueTap', [li]) !== false && valid) {
						val = idx;
					} else {
						hl = true;
					}

					if (hl && valid) {
						li.addClass('dw-hl'); // Highlight
						setTimeout(function() {
							li.removeClass('dw-hl');
						}, 100);
					}

					if (!multiple && (s.confirmOnTap === true || s.confirmOnTap[index]) && li.hasClass('dw-sel')) {
						that.select();
						return;
					}
				} else {
					val = constrain(Math.round(p - dist / itemHeight), min, max);
					time = speed ? Math.max(0.1, Math.abs((val - curr) / speed) * s.timeUnit) : 0.1;
				}

				if (isScrollable) {
					calc(target, index, val, 0, time, true);
				}
			}
		}

		function onBtnStart(ev) {
			btn = $(this);
			// +/- buttons
			if (testTouch(ev, this)) {
				step(ev, btn.closest('.dwwl'), btn.hasClass('dwwbp') ? plus : minus);
			}
			if (ev.type === 'mousedown') {
				$(document).on('mouseup', onBtnEnd);
			}
		}

		function onBtnEnd(ev) {
			btn = null;
			if (click) {
				clearInterval(timer);
				click = false;
			}
			if (ev.type === 'mouseup') {
				$(document).off('mouseup', onBtnEnd);
			}
		}

		function onKeyDown(ev) {
			if (ev.keyCode == 38) { // up
				step(ev, $(this), minus);
			} else if (ev.keyCode == 40) { // down
				step(ev, $(this), plus);
			}
		}

		function onKeyUp() {
			if (click) {
				clearInterval(timer);
				click = false;
			}
		}

		function onScroll(ev) {
			if (!isReadOnly(this)) {
				ev.preventDefault();
				ev = ev.originalEvent || ev;

				var delta = ev.deltaY || ev.wheelDelta || ev.detail,
					t = $('.dw-ul', this);

				setGlobals(t);

				scroll(t, index, constrain(((delta < 0 ? -20 : 20) - pixels[index]) / itemHeight, min - 1, max + 1));

				clearTimeout(scrollDebounce);
				scrollDebounce = setTimeout(function() {
					calc(t, index, Math.round(pos[index]), delta > 0 ? 1 : 2, 0.1);
				}, 200);
			}
		}

		// Private functions

		function step(ev, w, func) {
			ev.stopPropagation();
			ev.preventDefault();
			if (!click && !isReadOnly(w) && !w.hasClass('dwa')) {
				click = true;
				// + Button
				var t = w.find('.dw-ul');

				setGlobals(t);
				clearInterval(timer);
				timer = setInterval(function() {
					func(t);
				}, s.delay);
				func(t);
			}
		}

		function isReadOnly(wh) {
			if ($.isArray(s.readonly)) {
				var i = $('.dwwl', $markup).index(wh);
				return s.readonly[i];
			}
			return s.readonly;
		}

		function generateWheelItems(i) {
			var html = '<div class="dw-bf">',
				w = wheels[i],
				l = 1,
				labels = w.labels || [],
				values = w.values || [],
				keys = w.keys || values;

			$.each(values, function(j, v) {
				if (l % 20 === 0) {
					html += '</div><div class="dw-bf">';
				}
				html += '<div role="option" aria-selected="false" class="dw-li dw-v" data-val="' + keys[j] + '"' + (labels[j] ? ' aria-label="' + labels[j] + '"' : '') + ' style="height:' + itemHeight + 'px;line-height:' + itemHeight + 'px;">' +
					'<div class="dw-i"' + (lines > 1 ? ' style="line-height:' + Math.round(itemHeight / lines) + 'px;font-size:' + Math.round(itemHeight / lines * 0.8) + 'px;"' : '') + '>' + v + '</div></div>';
				l++;
			});

			html += '</div>';
			return html;
		}

		function setGlobals(t) {
			multiple = t.closest('.dwwl').hasClass('dwwms');
			min = $('.dw-li', t).index($(multiple ? '.dw-li' : '.dw-v', t).eq(0));
			max = Math.max(min, $('.dw-li', t).index($(multiple ? '.dw-li' : '.dw-v', t).eq(-1)) - (multiple ? s.rows - (s.mode == 'scroller' ? 1 : 3) : 0));
			index = $('.dw-ul', $markup).index(t);
		}

		function formatHeader(v) {
			var t = s.headerText;
			return t ? (typeof t === 'function' ? t.call(el, v) : t.replace(/\{value\}/i, v)) : '';
		}

		function getCurrentPosition(t) {
			return Math.round(-util.getPosition(t, true) / itemHeight);
		}

		function ready(t, i) {
			clearTimeout(iv[i]);
			delete iv[i];
			t.closest('.dwwl').removeClass('dwa');
		}

		function scroll(t, index, val, time, active) {
			var px = -val * itemHeight,
				style = t[0].style;

			if (px == pixels[index] && iv[index]) {
				return;
			}

			//if (time && px != pixels[index]) {
			// Trigger animation start event
			//trigger('onAnimStart', [$markup, index, time]);
			//}

			pixels[index] = px;

			if (has3d) {
				style[pr + 'Transition'] = util.prefix + 'transform ' + (time ? time.toFixed(3) : 0) + 's ease-out';
				style[pr + 'Transform'] = 'translate3d(0,' + px + 'px,0)';
			} else {
				style.top = px + 'px';
			}

			if (iv[index]) {
				ready(t, index);
			}

			if (time && active) {
				t.closest('.dwwl').addClass('dwa');
				iv[index] = setTimeout(function() {
					ready(t, index);
				}, time * 1000);
			}

			pos[index] = val;
		}

		function getValid(val, t, dir, multiple, select) {
			var selected,
				cell = $('.dw-li[data-val="' + val + '"]', t),
				cells = $('.dw-li', t),
				v = cells.index(cell),
				l = cells.length;

			if (multiple) {
				setGlobals(t);
			} else if (!cell.hasClass('dw-v')) { // Scroll to a valid cell
				var cell1 = cell,
					cell2 = cell,
					dist1 = 0,
					dist2 = 0;

				while (v - dist1 >= 0 && !cell1.hasClass('dw-v')) {
					dist1++;
					cell1 = cells.eq(v - dist1);
				}

				while (v + dist2 < l && !cell2.hasClass('dw-v')) {
					dist2++;
					cell2 = cells.eq(v + dist2);
				}

				// If we have direction (+/- or mouse wheel), the distance does not count
				if (((dist2 < dist1 && dist2 && dir !== 2) || !dist1 || (v - dist1 < 0) || dir == 1) && cell2.hasClass('dw-v')) {
					cell = cell2;
					v = v + dist2;
				} else {
					cell = cell1;
					v = v - dist1;
				}
			}

			selected = cell.hasClass('dw-sel');

			if (select) {
				if (!multiple) {
					$('.dw-sel', t).removeAttr('aria-selected');
					cell.attr('aria-selected', 'true');
				}

				// Add selected class to cell
				$('.dw-sel', t).removeClass('dw-sel');
				cell.addClass('dw-sel');
			}

			return {
				selected: selected,
				v: multiple ? constrain(v, min, max) : v,
				val: cell.hasClass('dw-v') ? cell.attr('data-val') : null
			};
		}

		function scrollToPos(time, index, manual, dir, active) {
			// Call validation event
			if (trigger('validate', [$markup, index, time, dir]) !== false) {
				// Set scrollers to position
				$('.dw-ul', $markup).each(function(i) {
					var t = $(this),
						multiple = t.closest('.dwwl').hasClass('dwwms'),
						sc = i == index || index === undefined,
						res = getValid(that._tempWheelArray[i], t, dir, multiple, true),
						selected = res.selected;

					if (!selected || sc) {
						// Set valid value
						that._tempWheelArray[i] = res.val;

						// Scroll to position
						scroll(t, i, res.v, sc ? time : 0.1, sc ? active : false);
					}
				});

				trigger('onValidated', []);

				// Reformat value if validation changed something
				that._tempValue = s.formatValue(that._tempWheelArray, that);

				if (that.live) {
					that._hasValue = manual || that._hasValue;
					setValue(manual, manual, 0, true);
				}

				that._header.html(formatHeader(that._tempValue));

				if (manual) {
					trigger('onChange', [that._tempValue]);
				}
			}

		}

		function calc(t, idx, val, dir, time, active) {
			val = constrain(val, min, max);

			// Set selected scroller value
			that._tempWheelArray[idx] = $('.dw-li', t).eq(val).attr('data-val');

			scroll(t, idx, val, time, active);

			setTimeout(function() {
				// Validate
				scrollToPos(time, idx, true, dir, active);
			}, 10);
		}

		function plus(t) {
			var val = pos[index] + 1;
			calc(t, index, val > max ? min : val, 1, 0.1);
		}

		function minus(t) {
			var val = pos[index] - 1;
			calc(t, index, val < min ? max : val, 2, 0.1);
		}

		function setValue(fill, change, time, noscroll, temp) {
			if (that._isVisible && !noscroll) {
				scrollToPos(time);
			}

			that._tempValue = s.formatValue(that._tempWheelArray, that);

			if (!temp) {
				that._wheelArray = that._tempWheelArray.slice(0);
				that._value = that._hasValue ? that._tempValue : null;
			}

			if (fill) {

				trigger('onValueFill', [that._hasValue ? that._tempValue : '', change]);

				if (that._isInput) {
					$elm.val(that._hasValue ? that._tempValue : '');
				}

				if (change) {
					that._preventChange = true;
					$elm.change();
				}
			}
		}

		// Call the parent constructor
		classes.Frame.call(this, el, settings, true);

		// Public functions

		/**
		 * Gets the selected wheel values, formats it, and set the value of the scroller instance.
		 * If input parameter is true, populates the associated input element.
		 * @param {Array} values Wheel values.
		 * @param {Boolean} [fill=false] Also set the value of the associated input element.
		 * @param {Number} [time=0] Animation time
		 * @param {Boolean} [temp=false] If true, then only set the temporary value.(only scroll there but not set the value)
		 * @param {Boolean} [change=false] Trigger change on the input element
		 */
		that.setVal = that._setVal = function(val, fill, change, temp, time) {
			that._hasValue = val !== null && val !== undefined;
			that._tempWheelArray = $.isArray(val) ? val.slice(0) : s.parseValue.call(el, val, that) || [];
			setValue(fill, change === undefined ? fill : change, time, false, temp);
		};

		/**
		 * Returns the selected value
		 */
		that.getVal = that._getVal = function(temp) {
			var val = that._hasValue || temp ? that[temp ? '_tempValue' : '_value'] : null;
			return util.isNumeric(val) ? +val : val;
		};

		/*
		 * Sets the wheel values (passed as an array)
		 */
		that.setArrayVal = that.setVal;

		/*
		 * Returns the selected wheel values as an array
		 */
		that.getArrayVal = function(temp) {
			return temp ? that._tempWheelArray : that._wheelArray;
		};

		// @deprecated since 2.14.0, backward compatibility code
		// ---

		that.setValue = function(val, fill, time, temp, change) {
			that.setVal(val, fill, change, temp, time);
		};

		/**
		 * Return the selected wheel values.
		 */
		that.getValue = that.getArrayVal;

		// ---

		/**
		 * Changes the values of a wheel, and scrolls to the correct position
		 * @param {Array} idx Indexes of the wheels to change.
		 * @param {Number} [time=0] Animation time when scrolling to the selected value on the new wheel.
		 * @param {Boolean} [manual=false] Indicates that the change was triggered by the user or from code.
		 */
		that.changeWheel = function(idx, time, manual) {
			if ($markup) {
				var i = 0,
					nr = idx.length;

				$.each(s.wheels, function(j, wg) {
					$.each(wg, function(k, w) {
						if ($.inArray(i, idx) > -1) {
							wheels[i] = w;
							$('.dw-ul', $markup).eq(i).html(generateWheelItems(i));
							nr--;
							if (!nr) {
								that.position();
								scrollToPos(time, undefined, manual);
								return false;
							}
						}
						i++;
					});
					if (!nr) {
						return false;
					}
				});
			}
		};

		/**
		 * Returns the closest valid cell.
		 */
		that.getValidCell = getValid;

		that.scroll = scroll;

		// Protected overrides

		that._generateContent = function() {
			var lbl,
				html = '',
				l = 0;

			$.each(s.wheels, function(i, wg) { // Wheel groups
				html += '<div class="mbsc-w-p dwc' + (s.mode != 'scroller' ? ' dwpm' : ' dwsc') + (s.showLabel ? '' : ' dwhl') + '">' +
					'<div class="dwwc"' + (s.maxWidth ? '' : ' style="max-width:600px;"') + '>' +
					(hasFlex ? '' : '<table class="dw-tbl" cellpadding="0" cellspacing="0"><tr>');

				$.each(wg, function(j, w) { // Wheels
					wheels[l] = w;
					lbl = w.label !== undefined ? w.label : j;
					html += '<' + (hasFlex ? 'div' : 'td') + ' class="dwfl"' + ' style="' +
						(s.fixedWidth ? ('width:' + (s.fixedWidth[l] || s.fixedWidth) + 'px;') :
							(s.minWidth ? ('min-width:' + (s.minWidth[l] || s.minWidth) + 'px;') : 'min-width:' + s.width + 'px;') +
							(s.maxWidth ? ('max-width:' + (s.maxWidth[l] || s.maxWidth) + 'px;') : '')) + '">' +
						'<div class="dwwl dwwl' + l + (w.multiple ? ' dwwms' : '') + '">' +
						(s.mode != 'scroller' ?
							'<div class="dwb-e dwwb dwwbp ' + (s.btnPlusClass || '') + '" style="height:' + itemHeight + 'px;line-height:' + itemHeight + 'px;"><span>+</span></div>' + // + button
							'<div class="dwb-e dwwb dwwbm ' + (s.btnMinusClass || '') + '" style="height:' + itemHeight + 'px;line-height:' + itemHeight + 'px;"><span>&ndash;</span></div>' : '') + // - button
						'<div class="dwl">' + lbl + '</div>' + // Wheel label
						'<div tabindex="0" aria-live="off" aria-label="' + lbl + '" role="listbox" class="dwww">' +
						'<div class="dww" style="height:' + (s.rows * itemHeight) + 'px;">' +
						'<div class="dw-ul" style="margin-top:' + (w.multiple ? (s.mode == 'scroller' ? 0 : itemHeight) : s.rows / 2 * itemHeight - itemHeight / 2) + 'px;">';

					// Create wheel values
					html += generateWheelItems(l) +
						'</div></div><div class="dwwo"></div></div><div class="dwwol"' +
						(s.selectedLineHeight ? ' style="height:' + itemHeight + 'px;margin-top:-' + (itemHeight / 2 + (s.selectedLineBorder || 0)) + 'px;"' : '') + '></div></div>' +
						(hasFlex ? '</div>' : '</td>');

					l++;
				});

				html += (hasFlex ? '' : '</tr></table>') + '</div></div>';
			});

			return html;
		};

		that._attachEvents = function($markup) {
			$markup
				.on('keydown', '.dwwl', onKeyDown)
				.on('keyup', '.dwwl', onKeyUp)
				.on('touchstart mousedown', '.dwwl', onStart)
				.on('touchmove', '.dwwl', onMove)
				.on('touchend', '.dwwl', onEnd)
				.on('touchstart mousedown', '.dwwb', onBtnStart)
				.on('touchend', '.dwwb', onBtnEnd);

			if (s.mousewheel) {
				$markup.on('wheel mousewheel', '.dwwl', onScroll);
			}
		};

		that._markupReady = function($m) {
			$markup = $m;
			scrollToPos();
		};

		that._fillValue = function() {
			that._hasValue = true;
			setValue(true, true, 0, true);
		};

		that._readValue = function() {
			var v = $elm.val() || '';

			if (v !== '') {
				that._hasValue = true;
			}

			that._tempWheelArray = that._hasValue && that._wheelArray ? that._wheelArray.slice(0) : s.parseValue.call(el, v, that) || [];
			setValue();
		};

		that._processSettings = function() {
			s = that.settings;
			trigger = that.trigger;
			itemHeight = s.height;
			lines = s.multiline;

			that._isLiquid = (s.layout || (/top|bottom/.test(s.display) && s.wheels.length == 1 ? 'liquid' : '')) === 'liquid';

			// @deprecated since 2.15.0, backward compatibility code
			// ---
			if (s.formatResult) {
				s.formatValue = s.formatResult;
			}
			// ---

			if (lines > 1) {
				s.cssClass = (s.cssClass || '') + ' dw-ml';
			}

			// Ensure a minimum number of 3 items if clickpick buttons present
			if (s.mode != 'scroller') {
				s.rows = Math.max(3, s.rows);
			}
		};

		// Properties

		that._selectedValues = {};

		// Constructor
		if (!inherit) {
			that.init(settings);
		}
	};

	// Extend defaults
	classes.Scroller.prototype = {
		_hasDef: true,
		_hasTheme: true,
		_hasLang: true,
		_hasPreset: true,
		_class: 'scroller',
		_defaults: $.extend({}, classes.Frame.prototype._defaults, {
			// Options
			minWidth: 80,
			height: 40,
			rows: 3,
			multiline: 1,
			delay: 300,
			readonly: false,
			showLabel: true,
			confirmOnTap: true,
			wheels: [],
			mode: 'scroller',
			preset: '',
			speedUnit: 0.0012,
			timeUnit: 0.08,
			formatValue: function(d) {
				return d.join(' ');
			},
			parseValue: function(value, inst) {
				var val = [],
					ret = [],
					i = 0,
					found,
					keys;

				if (value !== null && value !== undefined) {
					val = (value + '').split(' ');
				}

				$.each(inst.settings.wheels, function(j, wg) {
					$.each(wg, function(k, w) {
						keys = w.keys || w.values;
						found = keys[0]; // Default to first wheel value if not found
						$.each(keys, function(l, key) {
							if (val[i] == key) { // Don't do strict comparison
								found = key;
								return false;
							}
						});
						ret.push(found);
						i++;
					});
				});
				return ret;
			}
		})
	};

	ms.themes.scroller = ms.themes.frame;

})(jQuery, window, document);

(function($, undefined) {
	var ms = $.mobiscroll;

	ms.datetime = {
		defaults: {
			shortYearCutoff: '+10',
			monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
			monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
			dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
			dayNamesMin: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
			amText: 'am',
			pmText: 'pm',
			getYear: function(d) {
				return d.getFullYear();
			},
			getMonth: function(d) {
				return d.getMonth();
			},
			getDay: function(d) {
				return d.getDate();
			},
			getDate: function(y, m, d, h, i, s, u) {
				return new Date(y, m, d, h || 0, i || 0, s || 0, u || 0);
			},
			getMaxDayOfMonth: function(y, m) {
				return 32 - new Date(y, m, 32).getDate();
			},
			getWeekNumber: function(d) {
				// Copy date so don't modify original
				d = new Date(d);
				d.setHours(0, 0, 0);
				// Set to nearest Thursday: current date + 4 - current day number
				// Make Sunday's day number 7
				d.setDate(d.getDate() + 4 - (d.getDay() || 7));
				// Get first day of year
				var yearStart = new Date(d.getFullYear(), 0, 1);
				// Calculate full weeks to nearest Thursday
				return Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
			}
		},
		/**
		 * Format a date into a string value with a specified format.
		 * @param {String} format Output format.
		 * @param {Date} date Date to format.
		 * @param {Object} [settings={}] Settings.
		 * @return {String} Returns the formatted date string.
		 */
		formatDate: function(format, date, settings) {
			if (!date) {
				return null;
			}
			var s = $.extend({}, ms.datetime.defaults, settings),
				look = function(m) { // Check whether a format character is doubled
					var n = 0;
					while (i + 1 < format.length && format.charAt(i + 1) == m) {
						n++;
						i++;
					}
					return n;
				},
				f1 = function(m, val, len) { // Format a number, with leading zero if necessary
					var n = '' + val;
					if (look(m)) {
						while (n.length < len) {
							n = '0' + n;
						}
					}
					return n;
				},
				f2 = function(m, val, s, l) { // Format a name, short or long as requested
					return (look(m) ? l[val] : s[val]);
				},
				i,
				year,
				output = '',
				literal = false;

			for (i = 0; i < format.length; i++) {
				if (literal) {
					if (format.charAt(i) == "'" && !look("'")) {
						literal = false;
					} else {
						output += format.charAt(i);
					}
				} else {
					switch (format.charAt(i)) {
						case 'd':
							output += f1('d', s.getDay(date), 2);
							break;
						case 'D':
							output += f2('D', date.getDay(), s.dayNamesShort, s.dayNames);
							break;
						case 'o':
							output += f1('o', (date.getTime() - new Date(date.getFullYear(), 0, 0).getTime()) / 86400000, 3);
							break;
						case 'm':
							output += f1('m', s.getMonth(date) + 1, 2);
							break;
						case 'M':
							output += f2('M', s.getMonth(date), s.monthNamesShort, s.monthNames);
							break;
						case 'y':
							year = s.getYear(date);
							output += (look('y') ? year : (year % 100 < 10 ? '0' : '') + year % 100);
							//output += (look('y') ? date.getFullYear() : (date.getYear() % 100 < 10 ? '0' : '') + date.getYear() % 100);
							break;
						case 'h':
							var h = date.getHours();
							output += f1('h', (h > 12 ? (h - 12) : (h === 0 ? 12 : h)), 2);
							break;
						case 'H':
							output += f1('H', date.getHours(), 2);
							break;
						case 'i':
							output += f1('i', date.getMinutes(), 2);
							break;
						case 's':
							output += f1('s', date.getSeconds(), 2);
							break;
						case 'a':
							output += date.getHours() > 11 ? s.pmText : s.amText;
							break;
						case 'A':
							output += date.getHours() > 11 ? s.pmText.toUpperCase() : s.amText.toUpperCase();
							break;
						case "'":
							if (look("'")) {
								output += "'";
							} else {
								literal = true;
							}
							break;
						default:
							output += format.charAt(i);
					}
				}
			}
			return output;
		},
		/**
		 * Extract a date from a string value with a specified format.
		 * @param {String} format Input format.
		 * @param {String} value String to parse.
		 * @param {Object} [settings={}] Settings.
		 * @return {Date} Returns the extracted date.
		 */
		parseDate: function(format, value, settings) {
			var s = $.extend({}, ms.datetime.defaults, settings),
				def = s.defaultValue || new Date();

			if (!format || !value) {
				return def;
			}

			// If already a date object
			if (value.getTime) {
				return value;
			}

			value = (typeof value == 'object' ? value.toString() : value + '');

			var shortYearCutoff = s.shortYearCutoff,
				year = s.getYear(def),
				month = s.getMonth(def) + 1,
				day = s.getDay(def),
				doy = -1,
				hours = def.getHours(),
				minutes = def.getMinutes(),
				seconds = 0, //def.getSeconds(),
				ampm = -1,
				literal = false, // Check whether a format character is doubled
				lookAhead = function(match) {
					var matches = (iFormat + 1 < format.length && format.charAt(iFormat + 1) == match);
					if (matches) {
						iFormat++;
					}
					return matches;
				},
				getNumber = function(match) { // Extract a number from the string value
					lookAhead(match);
					var size = (match == '@' ? 14 : (match == '!' ? 20 : (match == 'y' ? 4 : (match == 'o' ? 3 : 2)))),
						digits = new RegExp('^\\d{1,' + size + '}'),
						num = value.substr(iValue).match(digits);

					if (!num) {
						return 0;
					}
					iValue += num[0].length;
					return parseInt(num[0], 10);
				},
				getName = function(match, s, l) { // Extract a name from the string value and convert to an index
					var names = (lookAhead(match) ? l : s),
						i;

					for (i = 0; i < names.length; i++) {
						if (value.substr(iValue, names[i].length).toLowerCase() == names[i].toLowerCase()) {
							iValue += names[i].length;
							return i + 1;
						}
					}
					return 0;
				},
				checkLiteral = function() {
					iValue++;
				},
				iValue = 0,
				iFormat;

			for (iFormat = 0; iFormat < format.length; iFormat++) {
				if (literal) {
					if (format.charAt(iFormat) == "'" && !lookAhead("'")) {
						literal = false;
					} else {
						checkLiteral();
					}
				} else {
					switch (format.charAt(iFormat)) {
						case 'd':
							day = getNumber('d');
							break;
						case 'D':
							getName('D', s.dayNamesShort, s.dayNames);
							break;
						case 'o':
							doy = getNumber('o');
							break;
						case 'm':
							month = getNumber('m');
							break;
						case 'M':
							month = getName('M', s.monthNamesShort, s.monthNames);
							break;
						case 'y':
							year = getNumber('y');
							break;
						case 'H':
							hours = getNumber('H');
							break;
						case 'h':
							hours = getNumber('h');
							break;
						case 'i':
							minutes = getNumber('i');
							break;
						case 's':
							seconds = getNumber('s');
							break;
						case 'a':
							ampm = getName('a', [s.amText, s.pmText], [s.amText, s.pmText]) - 1;
							break;
						case 'A':
							ampm = getName('A', [s.amText, s.pmText], [s.amText, s.pmText]) - 1;
							break;
						case "'":
							if (lookAhead("'")) {
								checkLiteral();
							} else {
								literal = true;
							}
							break;
						default:
							checkLiteral();
					}
				}
			}
			if (year < 100) {
				year += new Date().getFullYear() - new Date().getFullYear() % 100 +
					(year <= (typeof shortYearCutoff != 'string' ? shortYearCutoff : new Date().getFullYear() % 100 + parseInt(shortYearCutoff, 10)) ? 0 : -100);
			}
			if (doy > -1) {
				month = 1;
				day = doy;
				do {
					var dim = 32 - new Date(year, month - 1, 32).getDate();
					if (day <= dim) {
						break;
					}
					month++;
					day -= dim;
				} while (true);
			}
			hours = (ampm == -1) ? hours : ((ampm && hours < 12) ? (hours + 12) : (!ampm && hours == 12 ? 0 : hours));

			var date = s.getDate(year, month - 1, day, hours, minutes, seconds);

			if (s.getYear(date) != year || s.getMonth(date) + 1 != month || s.getDay(date) != day) {
				return def; // Invalid date
			}

			return date;
		}
	};

	// @deprecated since 2.11.0, backward compatibility code
	// ---
	ms.formatDate = ms.datetime.formatDate;
	ms.parseDate = ms.datetime.parseDate;
	// ---

})(jQuery);

(function($, undefined) {

	var ms = $.mobiscroll,
		datetime = ms.datetime,
		date = new Date(),
		defaults = {
			startYear: date.getFullYear() - 100,
			endYear: date.getFullYear() + 1,
			separator: ' ',
			// Localization
			dateFormat: 'mm/dd/yy',
			dateOrder: 'mmddy',
			timeWheels: 'hhiiA',
			timeFormat: 'hh:ii A',
			dayText: 'Day',
			monthText: 'Month',
			yearText: 'Year',
			hourText: 'Hours',
			minuteText: 'Minutes',
			ampmText: '&nbsp;',
			secText: 'Seconds',
			nowText: 'Now'
		},
		/**
		 * @class Mobiscroll.datetime
		 * @extends Mobiscroll
		 * Mobiscroll Datetime component
		 */
		preset = function(inst) {
			var that = $(this),
				html5def = {},
				format;
			// Force format for html5 date inputs (experimental)
			if (that.is('input')) {
				switch (that.attr('type')) {
					case 'date':
						format = 'yy-mm-dd';
						break;
					case 'datetime':
						format = 'yy-mm-ddTHH:ii:ss';
						break;
					case 'datetime-local':
						format = 'yy-mm-ddTHH:ii:ss';
						break;
					case 'month':
						format = 'yy-mm';
						html5def.dateOrder = 'mmyy';
						break;
					case 'time':
						format = 'HH:ii:ss';
						break;
				}
				// Check for min/max attributes
				var min = that.attr('min'),
					max = that.attr('max');
				if (min) {
					html5def.minDate = datetime.parseDate(format, min);
				}
				if (max) {
					html5def.maxDate = datetime.parseDate(format, max);
				}
			}

			// Set year-month-day order
			var i,
				k,
				keys,
				values,
				wg,
				start,
				end,
				hasTime,
				mins,
				maxs,
				orig = $.extend({}, inst.settings),
				s = $.extend(inst.settings, ms.datetime.defaults, defaults, html5def, orig),
				offset = 0,
				validValues = [],
				wheels = [],
				ord = [],
				o = {},
				innerValues = {},
				f = {
					y: getYear,
					m: getMonth,
					d: getDay,
					h: getHour,
					i: getMinute,
					s: getSecond,
					u: getMillisecond,
					a: getAmPm
				},
				invalid = s.invalid,
				valid = s.valid,
				p = s.preset,
				dord = s.dateOrder,
				tord = s.timeWheels,
				regen = dord.match(/D/),
				ampm = tord.match(/a/i),
				hampm = tord.match(/h/),
				hformat = p == 'datetime' ? s.dateFormat + s.separator + s.timeFormat : p == 'time' ? s.timeFormat : s.dateFormat,
				defd = new Date(),
				steps = s.steps || {},
				stepH = steps.hour || s.stepHour || 1,
				stepM = steps.minute || s.stepMinute || 1,
				stepS = steps.second || s.stepSecond || 1,
				zeroBased = steps.zeroBased,
				mind = s.minDate || new Date(s.startYear, 0, 1),
				maxd = s.maxDate || new Date(s.endYear, 11, 31, 23, 59, 59),
				minH = zeroBased ? 0 : mind.getHours() % stepH,
				minM = zeroBased ? 0 : mind.getMinutes() % stepM,
				minS = zeroBased ? 0 : mind.getSeconds() % stepS,
				maxH = getMax(stepH, minH, (hampm ? 11 : 23)),
				maxM = getMax(stepM, minM, 59),
				maxS = getMax(stepM, minM, 59);

			format = format || hformat;

			if (p.match(/date/i)) {

				// Determine the order of year, month, day wheels
				$.each(['y', 'm', 'd'], function(j, v) {
					i = dord.search(new RegExp(v, 'i'));
					if (i > -1) {
						ord.push({
							o: i,
							v: v
						});
					}
				});
				ord.sort(function(a, b) {
					return a.o > b.o ? 1 : -1;
				});
				$.each(ord, function(i, v) {
					o[v.v] = i;
				});

				wg = [];
				for (k = 0; k < 3; k++) {
					if (k == o.y) {
						offset++;
						values = [];
						keys = [];
						start = s.getYear(mind);
						end = s.getYear(maxd);
						for (i = start; i <= end; i++) {
							keys.push(i);
							values.push((dord.match(/yy/i) ? i : (i + '').substr(2, 2)) + (s.yearSuffix || ''));
						}
						addWheel(wg, keys, values, s.yearText);
					} else if (k == o.m) {
						offset++;
						values = [];
						keys = [];
						for (i = 0; i < 12; i++) {
							var str = dord.replace(/[dy]/gi, '').replace(/mm/, (i < 9 ? '0' + (i + 1) : i + 1) + (s.monthSuffix || '')).replace(/m/, i + 1 + (s.monthSuffix || ''));
							keys.push(i);
							values.push(str.match(/MM/) ? str.replace(/MM/, '<span class="dw-mon">' + s.monthNames[i] + '</span>') : str.replace(/M/, '<span class="dw-mon">' + s.monthNamesShort[i] + '</span>'));
						}
						addWheel(wg, keys, values, s.monthText);
					} else if (k == o.d) {
						offset++;
						values = [];
						keys = [];
						for (i = 1; i < 32; i++) {
							keys.push(i);
							values.push((dord.match(/dd/i) && i < 10 ? '0' + i : i) + (s.daySuffix || ''));
						}
						addWheel(wg, keys, values, s.dayText);
					}
				}
				wheels.push(wg);
			}

			if (p.match(/time/i)) {
				hasTime = true;

				// Determine the order of hours, minutes, seconds wheels
				ord = [];
				$.each(['h', 'i', 's', 'a'], function(i, v) {
					i = tord.search(new RegExp(v, 'i'));
					if (i > -1) {
						ord.push({
							o: i,
							v: v
						});
					}
				});
				ord.sort(function(a, b) {
					return a.o > b.o ? 1 : -1;
				});
				$.each(ord, function(i, v) {
					o[v.v] = offset + i;
				});

				wg = [];
				for (k = offset; k < offset + 4; k++) {
					if (k == o.h) {
						offset++;
						values = [];
						keys = [];
						for (i = minH; i < (hampm ? 12 : 24); i += stepH) {
							keys.push(i);
							values.push(hampm && i === 0 ? 12 : tord.match(/hh/i) && i < 10 ? '0' + i : i);
						}
						addWheel(wg, keys, values, s.hourText);
					} else if (k == o.i) {
						offset++;
						values = [];
						keys = [];
						for (i = minM; i < 60; i += stepM) {
							keys.push(i);
							values.push(tord.match(/ii/) && i < 10 ? '0' + i : i);
						}
						/*for (i = 1; i < 61; i += 1) {
						    keys.push(i);
						    values.push(tord.match(/ii/) && i < 10 ? '0' + i : i);
						}*/
						addWheel(wg, keys, values, s.minuteText);
					} else if (k == o.s) {
						offset++;
						values = [];
						keys = [];
						for (i = minS; i < 60; i += stepS) {
							keys.push(i);
							values.push(tord.match(/ss/) && i < 10 ? '0' + i : i);
						}
						addWheel(wg, keys, values, s.secText);
					} else if (k == o.a) {
						offset++;
						var upper = tord.match(/A/);
						addWheel(wg, [0, 1], upper ? [s.amText.toUpperCase(), s.pmText.toUpperCase()] : [s.amText, s.pmText], s.ampmText);
					}
				}

				wheels.push(wg);
			}

			function get(d, i, def) {
				if (o[i] !== undefined) {
					return +d[o[i]];
				}
				if (innerValues[i] !== undefined) {
					return innerValues[i];
				}
				if (def !== undefined) {
					return def;
				}
				return f[i](defd);
			}

			function addWheel(wg, k, v, lbl) {
				wg.push({
					values: v,
					keys: k,
					label: lbl
				});
			}

			function step(v, st, min, max) {
				return Math.min(max, Math.floor(v / st) * st + min);
			}

			function getYear(d) {
				return s.getYear(d);
			}

			function getMonth(d) {
				return s.getMonth(d);
			}

			function getDay(d) {
				return s.getDay(d);
			}

			function getHour(d) {
				var hour = d.getHours();
				hour = hampm && hour >= 12 ? hour - 12 : hour;
				return step(hour, stepH, minH, maxH);
			}

			function getMinute(d) {
				return step(d.getMinutes(), stepM, minM, maxM);
			}

			function getSecond(d) {
				return step(d.getSeconds(), stepS, minS, maxS);
			}

			function getMillisecond(d) {
				return d.getMilliseconds();
			}

			function getAmPm(d) {
				return ampm && d.getHours() > 11 ? 1 : 0;
			}

			function getDate(d) {
				if (d === null) {
					return d;
				}

				var year = get(d, 'y'),
					month = get(d, 'm'),
					day = Math.min(get(d, 'd', 1), s.getMaxDayOfMonth(year, month)),
					hour = get(d, 'h', 0);

				return s.getDate(year, month, day, get(d, 'a', 0) ? hour + 12 : hour, get(d, 'i', 0), get(d, 's', 0), get(d, 'u', 0));
			}

			function getMax(step, min, max) {
				return Math.floor((max - min) / step) * step + min;
			}

			function getClosestValidDate(d, dir) {
				var next,
					prev,
					nextValid = false,
					prevValid = false,
					up = 0,
					down = 0;

				// Normalize min and max dates for comparing later (set default values where there are no values from wheels)
				mind = getDate(getArray(mind));
				maxd = getDate(getArray(maxd));

				if (isValid(d)) {
					return d;
				}

				if (d < mind) {
					d = mind;
				}

				if (d > maxd) {
					d = maxd;
				}

				next = d;
				prev = d;

				if (dir !== 2) {
					nextValid = isValid(next);

					while (!nextValid && next < maxd) {
						next = new Date(next.getTime() + 1000 * 60 * 60 * 24);
						nextValid = isValid(next);
						up++;
					}
				}

				if (dir !== 1) {
					prevValid = isValid(prev);

					while (!prevValid && prev > mind) {
						prev = new Date(prev.getTime() - 1000 * 60 * 60 * 24);
						prevValid = isValid(prev);
						down++;
					}
				}

				if (dir === 1 && nextValid) {
					return next;
				}

				if (dir === 2 && prevValid) {
					return prev;
				}

				return down <= up && prevValid ? prev : next;
			}

			function isValid(d) {
				if (d < mind) {
					return false;
				}

				if (d > maxd) {
					return false;
				}

				if (isInObj(d, valid)) {
					return true;
				}

				if (isInObj(d, invalid)) {
					return false;
				}

				return true;
			}

			function isInObj(d, obj) {
				var curr,
					j,
					v;

				if (obj) {
					for (j = 0; j < obj.length; j++) {
						curr = obj[j];
						v = curr + '';
						if (!curr.start) {
							if (curr.getTime) { // Exact date
								if (d.getFullYear() == curr.getFullYear() && d.getMonth() == curr.getMonth() && d.getDate() == curr.getDate()) {
									return true;
								}
							} else if (!v.match(/w/i)) { // Day of month
								v = v.split('/');
								if (v[1]) {
									if ((v[0] - 1) == d.getMonth() && v[1] == d.getDate()) {
										return true;
									}
								} else if (v[0] == d.getDate()) {
									return true;
								}
							} else { // Day of week
								v = +v.replace('w', '');
								if (v == d.getDay()) {
									return true;
								}
							}
						}
					}
				}
				return false;
			}

			function validateDates(obj, y, m, first, maxdays, idx, val) {
				var j, d, v;

				if (obj) {
					for (j = 0; j < obj.length; j++) {
						d = obj[j];
						v = d + '';
						if (!d.start) {
							if (d.getTime) { // Exact date
								if (s.getYear(d) == y && s.getMonth(d) == m) {
									idx[s.getDay(d) - 1] = val;
								}
							} else if (!v.match(/w/i)) { // Day of month
								v = v.split('/');
								if (v[1]) {
									if (v[0] - 1 == m) {
										idx[v[1] - 1] = val;
									}
								} else {
									idx[v[0] - 1] = val;
								}
							} else { // Day of week
								v = +v.replace('w', '');
								for (k = v - first; k < maxdays; k += 7) {
									if (k >= 0) {
										idx[k] = val;
									}
								}
							}
						}
					}
				}
			}

			function validateTimes(vobj, i, v, temp, y, m, d, target, valid) {
				var dd, ss, str, parts1, parts2, prop1, prop2, v1, v2, j, i1, i2, add, remove, all, hours1, hours2, hours3,
					spec = {},
					steps = {
						h: stepH,
						i: stepM,
						s: stepS,
						a: 1
					},
					day = s.getDate(y, m, d),
					w = ['a', 'h', 'i', 's'];

				if (vobj) {
					$.each(vobj, function(i, obj) {
						if (obj.start) {
							obj.apply = false;
							dd = obj.d;
							ss = dd + '';
							str = ss.split('/');
							if (dd && ((dd.getTime && y == s.getYear(dd) && m == s.getMonth(dd) && d == s.getDay(dd)) || // Exact date
								(!ss.match(/w/i) && ((str[1] && d == str[1] && m == str[0] - 1) || (!str[1] && d == str[0]))) || // Day of month
								(ss.match(/w/i) && day.getDay() == +ss.replace('w', '')) // Day of week
							)) {
								obj.apply = true;
								spec[day] = true; // Prevent applying generic rule on day, if specific exists
							}
						}
					});

					$.each(vobj, function(x, obj) {
						add = 0;
						remove = 0;
						i1 = 0;
						i2 = undefined;
						prop1 = true;
						prop2 = true;
						all = false;

						if (obj.start && (obj.apply || (!obj.d && !spec[day]))) {

							// Define time parts
							parts1 = obj.start.split(':');
							parts2 = obj.end.split(':');

							for (j = 0; j < 3; j++) {
								if (parts1[j] === undefined) {
									parts1[j] = 0;
								}
								if (parts2[j] === undefined) {
									parts2[j] = 59;
								}
								parts1[j] = +parts1[j];
								parts2[j] = +parts2[j];
							}

							parts1.unshift(parts1[0] > 11 ? 1 : 0);
							parts2.unshift(parts2[0] > 11 ? 1 : 0);

							if (hampm) {
								if (parts1[1] >= 12) {
									parts1[1] = parts1[1] - 12;
								}

								if (parts2[1] >= 12) {
									parts2[1] = parts2[1] - 12;
								}
							}

							// Look behind
							for (j = 0; j < i; j++) {
								if (validValues[j] !== undefined) {
									v1 = step(parts1[j], steps[w[j]], mins[w[j]], maxs[w[j]]);
									v2 = step(parts2[j], steps[w[j]], mins[w[j]], maxs[w[j]]);
									hours1 = 0;
									hours2 = 0;
									hours3 = 0;
									if (hampm && j == 1) {
										hours1 = parts1[0] ? 12 : 0;
										hours2 = parts2[0] ? 12 : 0;
										hours3 = validValues[0] ? 12 : 0;
									}
									if (!prop1) {
										v1 = 0;
									}
									if (!prop2) {
										v2 = maxs[w[j]];
									}
									if ((prop1 || prop2) && (v1 + hours1 < validValues[j] + hours3 && validValues[j] + hours3 < v2 + hours2)) {
										all = true;
									}
									if (validValues[j] != v1) {
										prop1 = false;
									}
									if (validValues[j] != v2) {
										prop2 = false;
									}
								}
							}

							// Look ahead
							if (!valid) {
								for (j = i + 1; j < 4; j++) {
									if (parts1[j] > 0) {
										add = steps[v];
									}
									if (parts2[j] < maxs[w[j]]) {
										remove = steps[v];
									}
								}
							}

							if (!all) {
								// Calculate min and max values
								v1 = step(parts1[i], steps[v], mins[v], maxs[v]) + add;
								v2 = step(parts2[i], steps[v], mins[v], maxs[v]) - remove;

								if (prop1) {
									i1 = getValidIndex(target, v1, maxs[v], 0);
								}

								if (prop2) {
									i2 = getValidIndex(target, v2, maxs[v], 1);
								}
							}

							// Disable values
							if (prop1 || prop2 || all) {
								if (valid) {
									$('.dw-li', target).slice(i1, i2).addClass('dw-v');
								} else {
									$('.dw-li', target).slice(i1, i2).removeClass('dw-v');
								}
							}

						}
					});
				}
			}

			function getIndex(t, v) {
				return $('.dw-li', t).index($('.dw-li[data-val="' + v + '"]', t));
			}

			function getValidIndex(t, v, max, add) {
				if (v < 0) {
					return 0;
				}
				if (v > max) {
					return $('.dw-li', t).length;
				}
				return getIndex(t, v) + add;
			}

			function getArray(d, fillInner) {
				var ret = [];

				if (d === null || d === undefined) {
					return d;
				}

				$.each(['y', 'm', 'd', 'a', 'h', 'i', 's', 'u'], function(x, i) {
					if (o[i] !== undefined) {
						ret[o[i]] = f[i](d);
					}
					if (fillInner) {
						innerValues[i] = f[i](d);
					}
				});

				return ret;
			}

			function convertRanges(arr) {
				var i, v, start,
					ret = [];

				if (arr) {
					for (i = 0; i < arr.length; i++) {
						v = arr[i];
						if (v.start && v.start.getTime) {
							start = new Date(v.start);
							while (start <= v.end) {
								ret.push(new Date(start.getFullYear(), start.getMonth(), start.getDate()));
								start.setDate(start.getDate() + 1);
							}
						} else {
							ret.push(v);
						}
					}
					return ret;
				}
				return arr;
			}

			// Extended methods
			// ---

			inst.getVal = function(temp) {
				return inst._hasValue || temp ? getDate(inst.getArrayVal(temp)) : null;
			};

			/**
			 * Sets the selected date
			 *
			 * @param {Date} d Date to select.
			 * @param {Boolean} [fill=false] Also set the value of the associated input element. Default is true.
			 * @param {Number} [time=0] Animation time to scroll to the selected date.
			 * @param {Boolean} [temp=false] Set temporary value only.
			 * @param {Boolean} [change=fill] Trigger change on input element.
			 */
			inst.setDate = function(d, fill, time, temp, change) {
				inst.setArrayVal(getArray(d), fill, change, temp, time);
			};

			/**
			 * Returns the selected date.
			 *
			 * @param {Boolean} [temp=false] If true, return the currently shown date on the picker, otherwise the last selected one.
			 * @return {Date}
			 */
			inst.getDate = inst.getVal;

			// ---


			// Initializations
			// --- 

			inst.format = hformat;
			inst.order = o;

			inst.handlers.now = function() {
				inst.setDate(new Date(), false, 0.3, true, true);
			};
			inst.buttons.now = {
				text: s.nowText,
				handler: 'now'
			};

			invalid = convertRanges(invalid);
			valid = convertRanges(valid);

			mins = {
				y: mind.getFullYear(),
				m: 0,
				d: 1,
				h: minH,
				i: minM,
				s: minS,
				a: 0
			};
			maxs = {
				y: maxd.getFullYear(),
				m: 11,
				d: 31,
				h: maxH,
				i: maxM,
				s: maxS,
				a: 1
			};

			// ---

			return {
				wheels: wheels,
				headerText: s.headerText ? function() {
					return datetime.formatDate(hformat, getDate(inst.getArrayVal(true)), s);
				} : false,
				formatValue: function(d) {
					return datetime.formatDate(format, getDate(d), s);
				},
				parseValue: function(val) {
					if (!val) {
						innerValues = {};
					}
					return getArray(val ? datetime.parseDate(format, val, s) : (s.defaultValue || new Date()), !!val && !!val.getTime);
				},
				validate: function(dw, i, time, dir) {
					var validated = getClosestValidDate(getDate(inst.getArrayVal(true)), dir),
						temp = getArray(validated),
						y = get(temp, 'y'),
						m = get(temp, 'm'),
						minprop = true,
						maxprop = true;

					$.each(['y', 'm', 'd', 'a', 'h', 'i', 's'], function(x, i) {
						if (o[i] !== undefined) {
							var min = mins[i],
								max = maxs[i],
								maxdays = 31,
								val = get(temp, i),
								t = $('.dw-ul', dw).eq(o[i]);

							if (i == 'd') {
								maxdays = s.getMaxDayOfMonth(y, m);
								max = maxdays;
								if (regen) {
									$('.dw-li', t).each(function() {
										var that = $(this),
											d = that.data('val'),
											w = s.getDate(y, m, d).getDay(),
											str = dord.replace(/[my]/gi, '').replace(/dd/, (d < 10 ? '0' + d : d) + (s.daySuffix || '')).replace(/d/, d + (s.daySuffix || ''));
										$('.dw-i', that).html(str.match(/DD/) ? str.replace(/DD/, '<span class="dw-day">' + s.dayNames[w] + '</span>') : str.replace(/D/, '<span class="dw-day">' + s.dayNamesShort[w] + '</span>'));
									});
								}
							}
							if (minprop && mind) {
								min = f[i](mind);
							}
							if (maxprop && maxd) {
								max = f[i](maxd);
							}
							if (i != 'y') {
								var i1 = getIndex(t, min),
									i2 = getIndex(t, max);
								$('.dw-li', t).removeClass('dw-v').slice(i1, i2 + 1).addClass('dw-v');
								if (i == 'd') { // Hide days not in month
									$('.dw-li', t).removeClass('dw-h').slice(maxdays).addClass('dw-h');
								}
							}
							if (val < min) {
								val = min;
							}
							if (val > max) {
								val = max;
							}
							if (minprop) {
								minprop = val == min;
							}
							if (maxprop) {
								maxprop = val == max;
							}
							// Disable some days
							if (i == 'd') {
								var first = s.getDate(y, m, 1).getDay(),
									idx = {};

								// Set invalid indexes
								validateDates(invalid, y, m, first, maxdays, idx, 1);
								// Delete indexes which are valid 
								validateDates(valid, y, m, first, maxdays, idx, 0);

								$.each(idx, function(i, v) {
									if (v) {
										$('.dw-li', t).eq(i).removeClass('dw-v');
									}
								});
							}
						}
					});

					// Invalid times
					if (hasTime) {
						$.each(['a', 'h', 'i', 's'], function(i, v) {
							var val = get(temp, v),
								d = get(temp, 'd'),
								t = $('.dw-ul', dw).eq(o[v]);

							if (o[v] !== undefined) {
								validateTimes(invalid, i, v, temp, y, m, d, t, 0);
								validateTimes(valid, i, v, temp, y, m, d, t, 1);

								// Get valid value
								validValues[i] = +inst.getValidCell(val, t, dir).val;
							}
						});
					}

					inst._tempWheelArray = temp;
				}
			};
		};

	$.each(['date', 'time', 'datetime'], function(i, v) {
		ms.presets.scroller[v] = preset;
	});

})(jQuery);

(function($) {

	$.each(['date', 'time', 'datetime'], function(i, v) {
		$.mobiscroll.presetShort(v);
	});

})(jQuery);

(function($) {
	$.mobiscroll.i18n.zh = $.extend($.mobiscroll.i18n.zh, {
		// Core
		setText: '确定',
		cancelText: '取消',
		clearText: '明确',
		selectedText: '选',
		// Datetime component
		dateFormat: 'yy/mm/dd',
		dateOrder: 'yymmdd',
		dayNames: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
		dayNamesShort: ['日', '一', '二', '三', '四', '五', '六'],
		dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
		dayText: '日',
		hourText: '时',
		minuteText: '分',
		monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
		monthNamesShort: ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二'],
		monthText: '月',
		secText: '秒',
		timeFormat: 'HH:ii',
		timeWheels: 'HHii',
		yearText: '年',
		nowText: '当前',
		pmText: '下午',
		amText: '上午',
		// Calendar component
		dateText: '日',
		timeText: '时间',
		calendarText: '日历',
		closeText: '关闭',
		// Daterange component
		fromText: '开始时间',
		toText: '结束时间',
		// Measurement components
		wholeText: '合计',
		fractionText: '分数',
		unitText: '单位',
		// Time / Timespan component
		labels: ['年', '月', '日', '小时', '分钟', '秒', ''],
		labelsShort: ['年', '月', '日', '点', '分', '秒', ''],
		// Timer component
		startText: '开始',
		stopText: '停止',
		resetText: '重置',
		lapText: '圈',
		hideText: '隐藏',
		// Listview
		backText: '背部',
		undoText: '复原'
	});
})(jQuery);

/**
 * TODO
 */
var wtCityPick = {};
wtCityPick.pc = new Array();
wtCityPick.pc[0] = new Array("北京", "北京|东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
wtCityPick.pc[1] = new Array("上海", "上海|黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
wtCityPick.pc[2] = new Array("天津", "和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
wtCityPick.pc[3] = new Array("重庆", "万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
wtCityPick.pc[4] = new Array("河北", "石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
wtCityPick.pc[5] = new Array("山西", "太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
wtCityPick.pc[6] = new Array("内蒙古", "呼和浩特|包头|乌海|赤峰|呼伦贝尔|通辽|乌兰察布|鄂尔多斯|巴彦淖尔");
wtCityPick.pc[7] = new Array("辽宁", "沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
wtCityPick.pc[8] = new Array("吉林", "长春|吉林|四平|辽源|通化|白山|松原|白城");
wtCityPick.pc[9] = new Array("黑龙江", "哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河");
wtCityPick.pc[10] = new Array("江苏", "南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
wtCityPick.pc[11] = new Array("浙江", "杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
wtCityPick.pc[12] = new Array("安徽", "合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
wtCityPick.pc[13] = new Array("福建", "福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
wtCityPick.pc[14] = new Array("江西", "南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
wtCityPick.pc[15] = new Array("山东", "济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
wtCityPick.pc[16] = new Array("河南", "郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
wtCityPick.pc[17] = new Array("湖北", "武汉|宜昌|荆州|黄石|荆门|黄冈|十堰|随州|咸宁|孝感|鄂州|襄阳");
wtCityPick.pc[18] = new Array("湖南", "长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|张家界");
wtCityPick.pc[19] = new Array("广东", "广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
wtCityPick.pc[20] = new Array("广西", "南宁|柳州|桂林|梧州|北海|崇左|来宾|防城港|钦州|贵港|玉林|贺州|百色|河池");
wtCityPick.pc[21] = new Array("海南", "海口|三亚|三沙");
wtCityPick.pc[22] = new Array("四川", "成都|绵阳|自贡|攀枝花|泸州|德阳|广元|遂宁|内江|乐山|资阳|宜宾|南充|达川|雅安|巴中|广安|眉山");
wtCityPick.pc[23] = new Array("贵州", "贵阳|六盘水|遵义|安顺|铜仁|毕节");
wtCityPick.pc[24] = new Array("云南", "昆明|昭通|曲靖|玉溪|普洱|保山|丽江|临沧");
wtCityPick.pc[25] = new Array("西藏", "拉萨");
wtCityPick.pc[26] = new Array("陕西", "西安|铜川|宝鸡|咸阳|渭南|汉中|安康|商洛|延安|榆林");
wtCityPick.pc[27] = new Array("甘肃", "兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳");
wtCityPick.pc[28] = new Array("宁夏", "银川|石嘴山|吴忠|固原|中卫");
wtCityPick.pc[29] = new Array("青海", "西宁|海东");
wtCityPick.pc[30] = new Array("新疆", "乌鲁木齐|克拉玛依");
wtCityPick.pc[31] = new Array("其它", "澳门|香港|台湾");
wtCityPick.hotCityhtmls = "";
wtCityPick.provHtmls = "";
for (var j = 0; j < wtCityPick.pc.length; j++) {
	wtCityPick.provHtmls += "<li data-xuhao='" + j + "'><span class='wt-citypick-provname'>" + wtCityPick.pc[j][0] + "</span></li>";
}
wtCityPick.template = '<div class="wt-citypick" id="wtCitypick"><div class="wt-citypick-prov"><div>选择省份</div><div><ul id="wtCitypickProvList">' + wtCityPick.provHtmls + '</ul></div></div><span class="wt-citypick-close" id="wtCitypickClose">关闭</span></div>';
wtCityPick.cityInit = function(input, ifprovince, callback) {
	$("#" + input).click(function() {
		$("#wtCitypick").remove();
		$("body").append(wtCityPick.template);
		var _top = $(this).offset().top + 51,
			_left = $(this).offset().left,
			_width = $(window).width();
		if (_width - _left < 450) {
			$("#wtCitypick").css({
				"top": _top + "px",
				"right": "0px"
			}).addClass("wt-citypick-rightselector");
		} else {
			$("#wtCitypick").css({
				"top": _top + "px",
				"left": _left + "px"
			});
		}
		var label = "false";
		var citySlecetProvince = "";
		$("#wtCitypickProvList").on("click", ".wt-citypick-provname", function() {
			function createUl(_this) {
				_this.css({
					"background": "#fff",
					"border-color": "#d8d8d8",
					"border-bottom-color": "#fff",
					"position": "absolute",
					"top": "0",
					"left": "0",
					"z-index": "999999",
					"overflow": "hidden"
				});
				var xuhao = _this.parent("li").attr("data-xuhao"),
					cityArr = wtCityPick.pc[xuhao][1].split("|"),
					cityHtmls = "<ul id='wtCitypickProvCitys'>";
				citySlecetProvince = wtCityPick.pc[xuhao][0];
				for (var i = 0; i < cityArr.length; i++) {
					cityHtmls += "<li class='wt-citypick-cityname'>" + cityArr[i] + "</li>";
				}
				cityHtmls += "</ul>";
				$("#wtCitypickProvCitys").remove();
				_this.parent("li").append(cityHtmls);
			}
			if (label == "false") {
				label = "true";
				$(this).attr("now-item", "true");
				createUl($(this));
			} else {
				if ($(this).attr("now-item") == "" || $(this).attr("now-item") == "false" || $(this).attr("now-item") == undefined) {
					$(this).parents("#wtCitypickProvList").find("span").attr("now-item", "false");
					$(this).attr("now-item", "true");
					$("#wtCitypickProvList span").css({
						"background": "",
						"border-color": "",
						"border-bottom-color": "",
						"position": "",
						"top": "",
						"left": "",
						"z-index": ""
					});
					$("#wtCitypickProvCitys").remove();
					createUl($(this));
				} else {
					label = "false";
					$(this).css({
						"background": "",
						"border-color": "",
						"border-bottom-color": "",
						"position": "",
						"top": "",
						"left": "",
						"z-index": ""
					});
					$("#wtCitypickProvCitys").remove();
				}
			}
		});
		var _input = input;
		$("#wtCitypick").on("click", ".wt-citypick-cityname", function(e) {
			e.stopPropagation();
			if (ifprovince) {
				var result = "";
				if (citySlecetProvince == $(this).html()) {
					result = citySlecetProvince;
				} else {
					result = citySlecetProvince + " " + $(this).html();
				}
				$("#" + _input).val(result);
			} else {
				$("#" + _input).val($(this).html());
			}
			$("#wtCitypick").remove();
			if (callback) {
				callback();
			}
		});
		$("#wtCitypickClose").on("click", function(e) {
			$("#wtCitypick").remove();
		});
	});
};

/**
 * TODO
 */
var wtSelect = {};
wtSelect.Init = function(_div, _dataArr, args) {
	$("#" + _div).addClass("wt-sel");
	var optshtmls = "<ol class='wt-sel-opts'>";
	for (var j = 0; j < _dataArr.length; j++) {
		if (j % 2 == 0) {
			optshtmls += "<li class='wt-sel-opt wt-sel-opt2'>" + _dataArr[j] + "</li>";
		} else {
			optshtmls += "<li class='wt-sel-opt'>" + _dataArr[j] + "</li>";
		}
	}
	var defaultValue = "请选择";
	if (args.value) {
		defaultValue = args.value;
	}
	optshtmls += "</ol>";
	var resultHtml = "";
	if (args.inputname) {
		resultHtml = '<input type="hidden" name="' + args.inputname + '"';
		if (args.value) {
			resultHtml += ' value="' + defaultValue + '"';
		}
		resultHtml += ' />';
	}
	resultHtml = resultHtml + '<div class="wt-sel-optarr"></div><div class="wt-sel-optvalue">' + defaultValue + '</div>' + optshtmls;
	$("#" + _div).html(resultHtml);
	var ol = $("#" + _div + " .wt-sel-opts");
	$("#" + _div + " .wt-sel-optarr,#" + _div + " .wt-sel-optvalue").click(
		function() {
			ol.toggle();
		});
	$("#" + _div + " .wt-sel-opt").click(function() {
		var text = $(this).html();
		$("#" + _div + " .wt-sel-optvalue").html(text);
		$("#" + _div + " input").val(text);
		$("#" + _div + " .wt-sel-opts").css("display", "none");
		if (args.callback) {
			args.callback();
		}
	});
	$("body").click(function(e) {
		if (!$(e.target).closest("#" + _div).length) {
			ol.css("display", "none");
		}
	});
};

/**
 * TODO
 */
function wtAlert(info, callback) {
	var html = '';
	html += '<div class="wt-win-bak"></div>';
	html += '<div class="wt-win">';
	html += '<div class="wt-win-body">';
	html += '<p>' + info + '</p><span class="wt-win-btn" id="wtAlertRightBtn">确认</span></div></div>';
	$("body").append(html);
	$("#wtAlertRightBtn").click(function() {
		wtHideWin();
		if (callback) {
			callback();
		}
	});
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 27) {
			wtHideWin();
			if (callback) {
				callback();
			}
		}
	};
}

function wtHideWin(callback) {
	$(".wt-win,.wt-win-bak").remove();
	document.onkeydown = null;
	if (callback) {
		callback();
	}
}

function wtConfirm(info, callback) {
	var html = '';
	html += '<div class="wt-win-bak"></div>';
	html += '<div class="wt-win">';
	html += '<div class="wt-win-body">';
	html += '<p>' + info + '</p><div class="wt-win-btns"><div><span id="wtConfirmRightBtn" class="wt-win-btn">确认</span></div>' + '<div><span class="wt-win-btn" onclick="wtHideWin()">取消</span></div></div></div></div>';
	$("body").append(html);
	if (callback) {
		$("#wtConfirmRightBtn").click(function() {
			wtHideWin();
			callback();
		});
	}
	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 27) {
			wtHideWin();
			if (callback) {
				callback();
			}
		};
	};
}



/**
 * 	TODO
 */
var wtJquery = {
	Ajax: function(_url, _parameter, _seccessFunction, _errorFunction) {
		var errorFunTemp = null;
		if (_errorFunction == null || _errorFunction == undefined) {
			errorFunTemp = wtJquery.ajaxFailed;
		} else {
			errorFunTemp = _errorFunction;
		}
		$.ajax({
			url: _url,
			type: "post",
			dataType: "json",
			data: _parameter,
			success: _seccessFunction,
			error: errorFunTemp
		});
	},
	ajaxFailed: function(_data) {
		/**
		 * alert(data.responseText);
		 * @return {TypeName}
		 */
	},
	/**
	 * 获取屏幕宽度
	 *
	 * @returns
	 */
	ScreenW: function() {
		return $(window).width();
	},
	/**
	 * 获取屏幕高度
	 *
	 * @returns
	 */
	ScreenH: function() {
		return $(window).height();
	},
	SetHeight: function(_id, _height) {
		$("#" + _id).height(_height);
	},
	SetWidth: function(_id, _width) {
		$("#" + _id).width(_width);
	},
	/**
	 * 数字
	 *
	 * @param {Object}
	 *            _id
	 * @memberOf {TypeName}
	 */
	ValidateNumber: function(_id) {
		$("#" + _id).keyup(function() {
			$(this).val($(this).val().replace(/\D|^0/g, ''));
		}).bind("paste", function() {
			/** CTR+V事件处理 */
			$(this).val($(this).val().replace(/\D|^0/g, ''));
		}).css("ime-mode", "disabled");
		/** CSS设置输入法不可用 */
	},
	/**
	 * 数字
	 *
	 * @param {Array}
	 *            _ids
	 */
	ValidateNumbers: function(_ids) {
		for (var i = 0; i < _ids.length; i++) {
			wtJquery.ValidateNumber(_ids[i]);
		}
	},
	/**
	 * 数字,可以0开头
	 *
	 * @param {Array}
	 *            _ids
	 */
	ValidateNumberWithZeroStart: function(_id) {
		$("#" + _id).keyup(function() {
			$(this).val($(this).val().replace(/\D/g, ''));
		}).bind("paste", function() {
			/** CTR+V事件处理 */
			$(this).val($(this).val().replace(/\D/g, ''));
		}).css("ime-mode", "disabled");
		/** CSS设置输入法不可用 */
	},
	/**
	 * 数字,可以0开头
	 *
	 * @param {Array}
	 *            _ids
	 */
	ValidateNumberWithZeroStarts: function(_ids) {
		for (var i = 0; i < _ids.length; i++) {
			wtJquery.ValidateNumberWithZeroStart(_ids[i]);
		}
	},
	/**
	 * 数字和小数点
	 *
	 * @param {Object}
	 *            _id
	 * @memberOf {TypeName}
	 */
	ValidateNumber2: function(_id) {
		$("#" + _id).keyup(function() {
			$(this).val($(this).val().replace(/[^0-9.]/g, ''));
		}).bind("paste", function() {
			/** CTR+V事件处理 */
			$(this).val($(this).val().replace(/[^0-9.]/g, ''));
		}).css("ime-mode", "disabled");
		/** CSS设置输入法不可用 */
	},
	/**
	 * 数字和小数点
	 *
	 * @param {Array}
	 *            _ids
	 */
	ValidateNumber2s: function(_ids) {
		for (var i = 0; i < _ids.length; i++) {
			wtJquery.ValidateNumber2(_ids[i]);
		}
	},
	GetTextValue: function(_id) {
		return $("#" + _id).val();
	},
	ChangeBtn: function(id, disabled, text) {
		if (disabled) {
			$("#" + id).removeAttr("disabled");
		} else {
			$("#" + id).attr("disabled", "disabled");
		}
		$("#" + id).val(text);
	}
};

var wtCom = {
	LoadJs: function(_url) {
		var isWinRT = (typeof Windows === "undefined") ? false : true;
		if (!isWinRT) {
			var script = '<' + 'script type="text/javascript" src="' + _url + '"' + '><' + '/script>';
			document.writeln(script);
		} else {
			var script = document.createElement("script");
			script.src = _url;
			document.getElementsByTagName("HEAD")[0].appendChild(script);
		}
	},
	SetEnterBtnEvent: function(enterFun) {
		document.onkeydown = function(event) {
			var e = event || window.event || arguments.callee.caller.arguments[0];
			/**
			 * if (e && e.keyCode == 27) { // 按 Esc //要做的事情 } if (e && e.keyCode ==
			 * 113) { // 按 F2 //要做的事情 }
			 */
			/**
			 * enter 键
			 */
			if (e && e.keyCode == 13) {
				enterFun();
			}
		}
	},
	SetCookie: function(name, value) {
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	},
	SetTempCookie: function(name, value) {
		document.cookie = name + "=" + escape(value);
	},
	GetCookie: function(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
		if (arr = document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	},
	DelCookie: function(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = wtCom.GetCookie(name);
		if (cval != null)
			document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
	},
	/**
	 * 更改Url，若支持Html5的异步刷新url，则修改URL，不跳转页面，反之跳转到该url
	 * @param {Object} _url
	 */
	ChangeUrl: function(_url) {
		if (window.history.pushState) {
			window.history.pushState(null, null, _url);
			return true;
		} else {
			wtCom.OpenUrl(_url, false);
			return false;
		}
	},
	RefreshPage: function() {
		location.reload(true);
	},
	/**
	 * 打开新页面
	 *
	 * @param {Object}
	 *            url 地址
	 * @param {Object}
	 *            ifNew 是否在新窗口中打开
	 */
	OpenUrl: function(url, ifNew) {
		if (ifNew) {
			window.open(url);
		} else {
			if (1 == 2) {
				self.location = url;
			}
			top.window.location.href = url;
		}
	},
	JudgeNull: function(_judgeValue, _defaultValue) {
		if (_judgeValue == undefined || _judgeValue == null) {
			return _defaultValue;
		} else {
			return _judgeValue;
		}
	},
	GetUrlParameter: function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return unescape(r[2]);
		}
		return null;
	},
	CheckFailed: function() {
		wtCom.OpenUrl("404.html", false);
	},
	ChangeTwoDecimal: function(number) {
		var f_x = parseFloat(number);
		if (isNaN(f_x)) {
			/**
			 * alert('function:changeTwoDecimal->parameter error');
			 */
			return false;
		}
		var f_x = Math.round(number * 100) / 100;
		return f_x;
	},
	JumpToHash: function(_name) {
		location.hash = _name;
	}
};

var wtDate = {
	StringToDate: function(DateStr) {
		var thisDate = DateStr.split(" ");
		var arys = thisDate[0].split('-');
		var arys2 = thisDate[1].split(':');
		myDate = new Date(arys[0], parseInt(arys[1]) - 1, arys[2], arys2[0], arys2[1]);

		/*var converted = Date.parse(DateStr);
		var myDate = new Date(converted);
		if (isNaN(myDate)) {
			//var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
			var arys = DateStr.split('-');
			myDate = new Date(arys[0], arys[1], arys[2]);
		}*/
		return myDate;
	},
	/**
	 * 获取当前日期字符串：20130121(补0)
	 *
	 * @return 20130121(补0)
	 */
	NowStr: function(myDate) {
		if (myDate == undefined || myDate == null) {
			myDate = new Date();
		}
		return myDate.getFullYear() + wtDate.AddZero(myDate.getMonth() + 1) + WtDate.AddZero(myDate.getDate());
	},
	/**
	 * 获取当前日期字符串：20130121(不补0)
	 *
	 * @return 20130121(不补0)
	 */
	NowStr2: function(myDate) {
		if (myDate == undefined || myDate == null) {
			myDate = new Date();
		}
		return myDate.getFullYear() + "" + (myDate.getMonth() + 1) + "" + myDate.getDate();
	},
	/**
	 * 获取当前日期字符串：2013-01-21(补0)
	 *
	 * @return 2013-01-21(补0)
	 */
	NowStr3: function(myDate) {
		if (myDate == undefined || myDate == null) {
			myDate = new Date();
		}
		return myDate.getFullYear() + "-" + wtDate.AddZero(myDate.getMonth() + 1) + "-" + wtDate.AddZero(myDate.getDate());
	},
	/**
	 * 获取当前日期字符串：2013-1-21(不补0)
	 *
	 * @return 2013-1-21(不补0)
	 */
	NowStr4: function(myDate) {
		if (myDate == undefined || myDate == null) {
			myDate = new Date();
		}
		return myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
	},
	/**
	 * 补0，用于时间日期的补0。比如1——01
	 *
	 * @param {Object}
	 *            num
	 */
	AddZero: function(num) {
		if (num.toString().length == 1) {
			return "0" + num;
		} else {
			return num + "";
		}
	},
	/**
	 * 获取与今天差N天的日期
	 *
	 * @param {Object}
	 *            n
	 * @return {TypeName}
	 */
	AddDay: function(n, myDate) {
		if (myDate == undefined || myDate == null) {
			myDate = new Date();
		}
		var newDate = new Date((myDate / 1000 + 86400 * n) * 1000);
		return newDate;
	},
	/**
	 * 2013-12-05 —— 131205
	 *
	 * @param {Object}
	 *            myDate
	 * @return {TypeName}
	 */
	ChangeDateStrToInt: function(myDate) {
		return myDate.substr(2, 2) + myDate.substr(5, 2) + myDate.substr(8, 2);
	},
	/**
	 * 两个时间的天数差
	 * @param {Object} dateStart
	 * @param {Object} dateEnd
	 */
	GetDiffDays: function(dateStart, dateEnd) {
		return dateEnd.DateDiff('d', dateStart);
	},
	/**
	 * 两个时间的差
	 * @param {Object} dateStart
	 * @param {Object} dateEnd
	 */
	GetDiffHours: function(dateStart, dateEnd) {
		return dateEnd.DateDiff('h', dateStart);
	},
	/**
	 * 两个时间的分钟差
	 * @param {Object} dateStart
	 * @param {Object} dateEnd
	 */
	GetDiffMinutes: function(dateStart, dateEnd) {
		return dateEnd.DateDiff('n', dateStart);
	},
	/**
	 * 两个时间的秒差
	 * @param {Object} dateStart
	 * @param {Object} dateEnd
	 */
	GetDiffSeconds: function(dateStart, dateEnd) {
		return dateEnd.DateDiff('s', dateStart);
	}
};

Array.prototype.inArray = function(e) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == e)
			return true;
	}
	return false;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

Date.prototype.diff = function(date) {
	return (this.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
};
Date.prototype.DateDiff = function(strInterval, dtEnd) {
	var dtStart = this;
	if (typeof dtEnd == 'string') //如果是字符串转换为日期型  
	{
		dtEnd = StringToDate(dtEnd);
	}
	switch (strInterval) {
		case 's':
			return parseInt((dtEnd - dtStart) / 1000);
		case 'n':
			return parseInt((dtEnd - dtStart) / 60000);
		case 'h':
			return parseInt((dtEnd - dtStart) / 3600000);
		case 'd':
			return parseInt((dtEnd - dtStart) / 86400000);
		case 'w':
			return parseInt((dtEnd - dtStart) / (86400000 * 7));
		case 'm':
			return (dtEnd.getMonth() + 1) + ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12) - (dtStart.getMonth() + 1);
		case 'y':
			return dtEnd.getFullYear() - dtStart.getFullYear();
	}
};

String.prototype.trim = function String$trim() {
	if (arguments.length !== 0) {
		throw Error.parameterCount();
	}
	return this.replace(/^\s+|\s+$/g, '');
};

/**
 * 判断访问终端
 */
var wtBrowser = {
	Versions: function() {
		var u = navigator.userAgent,
			app = navigator.appVersion;
		return {
			trident: u.indexOf('Trident') > -1, //IE内核
			ie678: app.indexOf('MSIE 6.0') > -1 || app.indexOf('MSIE 7.0') > -1 || app.indexOf('MSIE 8.0') > -1,
			presto: u.indexOf('Presto') > -1, //opera内核
			webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
			mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
			iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
			iPad: u.indexOf('iPad') > -1, //是否iPad
			webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
			weiXin: u.indexOf('MicroMessenger') > -1
		};
	}(),
	Language: (navigator.browserLanguage || navigator.language).toLowerCase()
};


/* 
 * 日历，PC、mobile通用，上下翻月事件。其他事件需改进
 */
(function($) {
	function createMonthTable(args) {
		var dateNow = new Date();
		var yearNow = dateNow.getFullYear();
		var monthNow = dateNow.getMonth() + 1;
		var dayNow = dateNow.getDate();

		var $thisDate = args.date;
		var year = $thisDate.getFullYear();
		var month = $thisDate.getMonth() + 1;
		var day = $thisDate.getDate();
		var week = $thisDate.getDay();
		//获取1号是周几
		var date1 = new Date(year, month - 1, 1);
		var week1 = date1.getDay();
		//获取这个月的最大天数
		var date2 = new Date(year, month, 0);
		var maxDay = date2.getDate();
		var html = '<tr><th>' + args.day0 + '</th><th>' + args.day1 + '</th><th>' + args.day2 + '</th><th>' + args.day3 + '</th><th>' + args.day4 + '</th><th>' + args.day5 + '</th><th>' + args.day6 + '</th></tr>'
			//把这个月前面空的天数填充好
		var noneDays = new Array(week1 + 1);
		html += '<tr>' + noneDays.join('<td></td>');
		var monthDay = 1;
		//一共有多少个方块（包含空的和填了日子的）
		var tdNum = maxDay + week1;
		while (tdNum % 7 != 0) {
			tdNum++;
		}
		tdNum++;
		for (var i = week1 + 1; i < tdNum; i++) {
			if ((i - 1) % 7 == 0) {
				html += '<tr>';
			}
			var tdIdName = "wtCalendar_" + year + "" + month + "" + monthDay;
			if (monthDay > maxDay) {
				html += '<td></td>';
			} else if (year == yearNow && month == monthNow && monthDay == dayNow) {
				html += '<td class="wt-cal-date-today" id="' + tdIdName + '">' + monthDay + '</td>';
			} else {
				html += '<td id="' + tdIdName + '">' + monthDay + '</td>';
			}
			if (i % 7 == 0) {
				html += '</tr>';
			}
			monthDay++;
		}
		if (i % 7 != 0) {
			html += '</tr>';
		}
		return html;
	}

	var methods = {
		init: function(options) {
			var args = $.extend({
				date: new Date(),
				day1: "周一",
				day2: "周二",
				day3: "周三",
				day4: "周四",
				day5: "周五",
				day6: "周六",
				day0: "周日",
				upMonth: function() {

				},
				downMonth: function() {

				}
			}, options);
			return $(this).each(function() {
				var $this = $(this);
				var $thisDate = args.date;
				var year = $thisDate.getFullYear();
				var month = $thisDate.getMonth() + 1;

				//头部
				var html = '<div class="wt-cal-top"><span class="wt-cal-topleft">上个月</span><span class="wt-cal-topright">下个月</span><span class="wt-cal-title">' + year + '年' + month + '月</span></div>';
				html += '<table class="wt-cal-date">' + createMonthTable(args) + '</table>';
				$this.html(html);
				$this.addClass('wt-cal');

				$this.find('.wt-cal-topleft').bind("click", function() {
					args.date = new Date(args.date.getFullYear(), args.date.getMonth() - 1, 1);
					var yearTemp = args.date.getFullYear();
					var monthTemp = args.date.getMonth() + 1;
					$this.find('.wt-cal-title').html(yearTemp + "年" + monthTemp + "月");
					$this.find('.wt-cal-date').html(createMonthTable(args));
					args.upMonth(args.date);
				});
				$this.find('.wt-cal-topright').bind("click", function() {
					args.date = new Date(args.date.getFullYear(), args.date.getMonth() + 1, 1);
					var yearTemp = args.date.getFullYear();
					var monthTemp = args.date.getMonth() + 1;
					$this.find('.wt-cal-title').html(yearTemp + "年" + monthTemp + "月");
					$this.find('.wt-cal-date').html(createMonthTable(args));
					args.downMonth(args.date);
				});
			});
		},
		show: function() {
			alert(2);
		},
		hide: function() {
			alert(3);
		},
		update: function(content) {
			alert(4);
		}
	};

	$.fn.wtCalendar = function(method, options) {
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method' + method + 'does not exist on jQuery.wtCalendar');
		}
	};
})(jQuery);

/**
 * 选择数量插件。
 * @param {Object} $
 */
(function($) {
	$.fn.wtSelNum = function(options) {
		var args = $.extend({
			id: "",
			inputname: "",
			inputid: "",
			maxnum: 99999,
			minnum: 0,
			defaultval: 1,
			addfun: null,
			setwrite: false,
			minusfun: null,
			changefun: null
		}, options);
		return $(this).each(function() {
			var $this = $(this);
			var $inputname = args.inputname;
			var $maxnum = args.maxnum;
			var $minnum = args.minnum;
			var $defaultval = args.defaultval;
			var $inputid = args.inputid;
			var $id = args.id;
			var $addfun = args.addfun;
			var $minusfun = args.minusfun;
			var $changefun = args.changefun;
			var $setwrite = args.setwrite;
			if ($defaultval < $minnum) {
				$defaultval = $minnum;
			} else if ($defaultval > $maxnum) {
				$defaultval = $maxnum;
			}
			//加号减号
			var inputidHtml = "";
			if ($inputid != "") {
				inputidHtml = 'id="' + $inputid + '"';
			}
			var html = '<span class="wt-selnum-min">-</span><span class="wt-selnum-num">';
			if ($setwrite) {
				html += '<input class="wt-selnum-input" ' + inputidHtml + ' name="' + $inputname + '" value="' + $defaultval + '"/>';
			} else {
				html += '<input readonly="readonly" class="wt-selnum-input" ' + inputidHtml + ' name="' + $inputname + '" value="' + $defaultval + '"/>';
			}
			html += '</span><span class="wt-selnum-add">+</span>';
			$this.html(html);
			$this.addClass('wt-selnum');
			if ($defaultval == $minnum) {
				$this.find('.wt-selnum-min').addClass("wt-selnum-dis")
			} else if ($defaultval == $maxnum) {
				$this.find('.wt-selnum-add').addClass("wt-selnum-dis")
			}
			$this.find('.wt-selnum-add').bind("click", function() {
				var inputTemp = $this.find('.wt-selnum-input');
				var num = parseInt(inputTemp.val());
				if (num == $minnum) {
					$this.find('.wt-selnum-min').removeClass("wt-selnum-dis");
				}
				if (num + 1 == $maxnum) {
					$this.find('.wt-selnum-add').addClass("wt-selnum-dis");
				}
				if (num < $maxnum) {
					num++;
					inputTemp.val(num);
					if ($addfun) {
						$addfun($id, num);
					}
					if ($changefun) {
						$changefun(1, $id, num);
					}
				}
			});
			$this.find('.wt-selnum-min').bind("click", function() {
				var inputTemp = $this.find('.wt-selnum-input');
				var num = parseInt(inputTemp.val());
				if (num == $maxnum) {
					$this.find('.wt-selnum-add').removeClass("wt-selnum-dis");
				}
				if (num == $minnum + 1) {
					$this.find('.wt-selnum-min').addClass("wt-selnum-dis");
				}
				if (num > $minnum) {
					num--;
					inputTemp.val(num);
					if ($minusfun) {
						$minusfun($id, num);
					}
					if ($changefun) {
						$changefun(-1, $id, num);
					}
				}
			});
			if ($setwrite) {
				$this.find('.wt-selnum-input').bind("blur", function() {
					var inputTemp = $(this);
					var num = Number(inputTemp.val());
					if (!num) {
						num = $minnum;
					}
					if (num < $minnum) {
						num = $minnum;
					} else if (num > $maxnum) {
						num = $maxnum;
					}
					inputTemp.val(num);
					if ($changefun) {
						$changefun(0, $id, num);
					}
				});
			}
		});
	};
})(jQuery);

var wtWx = {
	SetWxJsConfig: function(index, args, sucFun, ifDebug) {
		var jsApiList = null;
		if (index == 1) {
			jsApiList = ['onMenuShareTimeline',
				'onMenuShareAppMessage',
				'onMenuShareQQ',
				'onMenuShareWeibo'
			];
		}
		var debugOpen = false;
		if (ifDebug) {
			debugOpen = true;
		}
		//		jsApiList: ['checkJsApi',
		//				'onMenuShareTimeline',
		//				'onMenuShareAppMessage',
		//				'onMenuShareQQ',
		//				'onMenuShareWeibo',
		//				'hideMenuItems',
		//				'showMenuItems',
		//				'hideAllNonBaseMenuItem',
		//				'showAllNonBaseMenuItem',
		//				'translateVoice',
		//				'startRecord',
		//				'stopRecord',
		//				'onRecordEnd',
		//				'playVoice',
		//				'pauseVoice',
		//				'stopVoice',
		//				'uploadVoice',
		//				'downloadVoice',
		//				'chooseImage',
		//				'previewImage',
		//				'uploadImage',
		//				'downloadImage',
		//				'getNetworkType',
		//				'openLocation',
		//				'getLocation',
		//				'hideOptionMenu',
		//				'showOptionMenu',
		//				'closeWindow',
		//				'scanQRCode',
		//				'chooseWXPay',
		//				'openProductSpecificView',
		//				'addCard',
		//				'chooseCard',
		//				'openCard'
		//			]
		wx.config({
			debug: debugOpen,
			appId: args.appId,
			nonceStr: args.nonceStr,
			timestamp: args.timestamp,
			signature: args.signature,
			jsApiList: jsApiList
		});
		wx.ready(function() {
			sucFun();
		});
		wx.error(function(res) {
			//alert(res)
			// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		});
	},
	//	$.ajax({
	//		url: "http://www.vxiaov.com/vvweb/vv/getWxSdk2",
	//		type: "post",
	//		dataType: "json",
	//		data: "id=wqvxiaov&url=" + location.href.split('#')[0],
	//		success: function(data) {
	//			var args = {
	//				appid: "",
	//				nonceStr: data.obj.nonceStr,
	//				timestamp: data.obj.timestamp,
	//				signature: data.obj.signature
	//			};
	//			vvJs.SetWxJsConfig(1, args, InitWxSuc);
	//		}
	//	});
	//
	//	function InitWxSuc() {
	//		var imgUrl = "http://www.vxiaov.com/cases/haixie/img/fx.jpg";
	//		var title = "碧然德水壶特惠";
	//		var desc = "一壶一芯130，一壶五芯236，全场顺丰包邮";
	//		vvJs.WxFx(title, desc, top.location.href, imgUrl, null, null);
	//	}
	WxFx: function(title, desc, url, imgUrl, sucFun, cancelFun) {
		wx.onMenuShareAppMessage({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
			trigger: function(res) {},
			success: function(res) {
				if (sucFun) {
					sucFun(1);
				}
			},
			cancel: function(res) {
				cancelFun();
			},
			fail: function(res) {}
		});
		wx.onMenuShareTimeline({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
			trigger: function(res) {},
			success: function(res) {
				if (sucFun) {
					sucFun(2);
				}
			},
			cancel: function(res) {
				cancelFun();
			},
			fail: function(res) {}
		});
		wx.onMenuShareQQ({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
			trigger: function(res) {},
			complete: function(res) {},
			success: function(res) {
				if (sucFun) {
					sucFun(3);
				}
			},
			cancel: function(res) {
				cancelFun();
			},
			fail: function(res) {}
		});
		wx.onMenuShareWeibo({
			title: title,
			desc: desc,
			link: url,
			imgUrl: imgUrl,
			trigger: function(res) {},
			complete: function(res) {},
			success: function(res) {
				if (sucFun) {
					sucFun(4);
				}
			},
			cancel: function(res) {
				cancelFun();
			},
			fail: function(res) {}
		});
	}
};
(function ($) {
	$.fn.digitsOnly = function () {
		$(this).each(function (i, elem) {
			var $this = $(elem);
			$this.keydown(function (e) {
				var allowed = [8, 9, 13, 35, 36, 37, 39, 45, 46]; // backspace, tab, enter, end, home, left, right, insert, delete
				var allowedWithShift = [9, 35, 36, 37, 39, 45, 46]; // tab, end, home, left, right, insert, delete
				var key = e.keyCode || e.which || e.charCode || 0; // different browsers may get key code in different way
				var i;

				for (i = 48; i < 58; i++) // 0-9
					allowed.push(i);
				for (i = 96; i < 106; i++) // numpad 0-9
					allowed.push(i);

				if ($this.data('decimal') == true) {
					allowed.push(110); // dot on the num pad
					allowed.push(190); // dot
				}

				if (!e.shiftKey && !e.ctrlKey) {
					if (allowed.indexOf(key) < 0)
						e.preventDefault();
				} else if (e.shiftKey && allowedWithShift.indexOf(key) < 0) {
					e.preventDefault();
				}
			});
		});
	};
})(jQuery);

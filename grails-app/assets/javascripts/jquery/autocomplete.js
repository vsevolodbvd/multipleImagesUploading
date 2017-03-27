(function ($) {
	$.fn.autocompleteInput = function () {
		$(this).each(function () {
			var $el = $(this);
			$el.autocomplete({
				source: function (request, response) {
                    var searchProp = $el.data('search-prop');
                    if (searchProp) searchProp = searchProp.split(',');
					var nameProp = $el.data('name-prop');
					$.ajax({
						url: $el.data('src'),
						type: 'POST',
						data: { term: request.term, max: 10, domainClassName: $el.data('domain'),
							searchProp: searchProp, nameProp: nameProp},
						success: function (data) {
							data = JSON.parse(data);
							response(jQuery.map(data, function (item) {
								return { label: item.label, value: item.label, id: item.id }
							}));
						}
					});
				},
				minLength: 1,
				select: function (e, ui) {
					e.preventDefault();
					$el.val(ui.item.value);
					var $hidden = $el.siblings('#' + $el.data('field-id'));
					if ($hidden.length == 0) {
						$hidden = $('<input type="hidden">').attr('id', $el.data('field-id')).attr('name', $el.data('field-name'));
						$el.before($hidden);
					}
					$hidden.val(ui.item.id).change();
					return false;
				}
			}).on('keyup', function () {
				if ($el.val() == '') {
					$el.siblings('#' + $el.data('field-id')).val('');
				}
			});
		});

		return $(this);
	};
})(jQuery);

jQuery(function ($) {
	$('input[type=text][autocomplete=on]').autocompleteInput();
});

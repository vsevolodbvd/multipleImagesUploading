function createTransloaditSignature(url, templateId) {
    $.ajax({
        type: 'POST',
        async: false,
        url: url,
        data: {templateId: templateId},
        success: function (data) {
            var $params = $("input[name='params']");
            var $signature = $("input[name='signature']");
            if ($params && $signature) {
                $params.val(data.params);
                $signature.val(data.signature);
            } else {
                console.log('Could not find input[name=params] or input[name=signature] in your form.');
            }
        },
        error: function(response) {
            showMsg(' ', response.responseText, 'error')
        }
    });
}

function imagePreview(e, elem, icon) {
    e.preventDefault();
    var $target = $(e.target);
    var image = elem.id;
    if ($target.is('.close')) {
        if (confirm('Are you sure you want to delete current image?')) {
            $('input[name=' + image +']').val('');
            $('#uniform-' + image + 'Upload span.filename').text('No file selected');
            var newFileInput = $('<input type="file"/>')
                .attr({name: image + 'Upload', id: image + 'Upload', size: '50'}).change(handleFileInputChangeEvent);
            $('#' + image + 'Upload').replaceWith(newFileInput);
            $(elem).addClass('hide');
            $('#' + image + 'Note').addClass('hide');
        }
    } else {
        $(elem).siblings('img.preview')
            .css({width: 'auto', height: 'auto'})
            .modalBox({
                iconClose: true,
                iconImg: icon
            });
        $('.iw-closeImg').attr('title', 'Close');
    }
}

// Updates image preview.
function handleFileInputChangeEvent(e) {
    uploadImage = true;
    e.preventDefault();
    var name = $(this).val();
    var source = this.id.replace('Upload', '');
    $('#uniform-' + this.id + ' span.filename').text(name.substring(name.lastIndexOf('\\') + 1, name.length));
    if (window.FileReader) {
        var files = this.files;
        if (files.length != 0 && $(this).valid()) {
            var file = files[0];
            var reader = new FileReader();
            reader.addEventListener('load', function (e) {
                var result = e.target.result;
                $('#' + source + ' img').attr('src', result);
                $('#' + source + 'Preview').attr('src', result);
                $('#' + source).removeClass('hide');
                $('#' + source + 'ImageNote').removeClass('hide');
            });
            reader.readAsDataURL(file);
        }
    }
}
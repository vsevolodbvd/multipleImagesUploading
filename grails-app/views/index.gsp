<html>
<head>
	<meta name="layout" content="main">
    <title>Images uploading form</title>
    <script type="text/javascript" src="//assets.transloadit.com/js/jquery.transloadit2-v2-latest.js" defer></script>

    <style>
    .imagePop { position: relative; display: inline-block; margin-right: 100%; margin-bottom: 20px; }
    .imagePop img { max-width: 230px; max-height: 230px; }
    .close { position: absolute; top: 5px; right: 5px; width: 18px; height: 18px; opacity: 0.5; background-color: white; }
    .close:hover, .close:focus { opacity: 0.7; }
    .preview { border: 4px solid black; }
    .note { color: #808080; font-size: 12px; }
    .error { color: red; font-size: 12px; font-weight: bold; }
    input.error { border: 1px solid red; }

    </style>
</head>

<body>

<g:form name='uploadingForm' controller="imageUploading" action="save" class="form-horizontal">
    <g:hiddenField name="params" value="${signatureMap?.params}"/>
    <g:hiddenField name="signature" value="${signatureMap?.signature}"/>

    <div class="control-group">
        <label for="firstImage" class="control-label">First image</label>
        <div class="controls">
            <input type="file" name="firstImageUpload" id="firstImageUpload"/>
            <g:hiddenField name="firstImage"/>
        </div>
    </div>

    <div class="control-group">
        <label for="secondImage" class="control-label">Second image</label>
        <div class="controls">
            <input type="file" name="secondImageUpload" id="secondImageUpload"/>
            <g:hiddenField name="secondImage"/>
        </div>
    </div>
</g:form>
<div class="col-md-6">
    <a href="#" onclick="$('form[name=uploadingForm]').submit();return false" class="btn btn-info">Upload</a>
</div>

<g:javascript>
	$(function () {
	    var $form = $('#uploadingForm');

	    $form.validate({
			submitHandler: function () {
					$form.transloadit({
						modal: true,
						wait: true,
						fields: false,
						autoSubmit: false,
						onSuccess: function (assembly) {
							$.post("${createLink(controller: 'imageUploading', action: 'uploadImages')}", {assembly: JSON.stringify(assembly)}, function (data) {
								$.each(data, function(index, value) {
								 	var filedId = value.field.replace("Upload", "");
								 	$form.find($('input[name='+filedId+']')).val(value.url)
								});

								$form.unbind('submit.transloadit')[0].submit();
							});
						}
					}).trigger('submit.transloadit');
			}
		});
	});
</g:javascript>

</body>
</html>
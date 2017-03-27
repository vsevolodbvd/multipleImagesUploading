<g:if test="${flash.message || flash.error}">
	<div class='alert alert-${type ?: 'info'}'>
		<button class='close' data-dismiss='alert'>&times;</button>
		${flash.message ?: flash.error}
	</div>
</g:if>
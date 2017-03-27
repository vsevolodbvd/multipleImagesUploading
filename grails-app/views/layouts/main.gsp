<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

<div id="content">
    <div class="container-fluid col-md-6">
        <div class="row-fluid">
            <g:render template="/common/flash"/>
        </div>

        <div class="row-fluid col-md-6">
            <div class="col-md-12">
                <g:layoutBody/>
            </div>
        </div>
    </div>
</div>

    <asset:javascript src="application.js"/>

</body>
</html>

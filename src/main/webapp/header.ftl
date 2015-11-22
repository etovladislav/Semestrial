
<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img class="navbar-brand" src="/image/logo.png" alt=""/>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
            <#if auth??>
                <li><a href="/im"><i class="fa fa-home"></i> Profile</a></li>
                <li><a href="/news"><i class="fa fa-newspaper-o"></i> News</a></li>
                <li><a href="/all-users"><i class="fa fa-globe"></i> All users</a></li>
                <li><a href="/settings"><i class="fa fa-cogs"></i> Settings</a></li>
                <li><a href="/logout"><i class="fa fa-sign-out"></i> Logout</a></li>
            <#else>
                <li><a href="/auth"><i class="fa fa-sign-in"></i> Log in</a></li>
                <li><a href="/registration"><i class="fa fa-paper-plane"></i> Registration</a></li>
            </#if>
            </ul>
        </div>
    </div>
</div>
<#include "main-template.ftl"/>
<#macro m_body>
<div class="container" style="margin-top: 150px;">
    <div class="row">
        <form class="form-signin" role="form" action="/auth" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>

            <div class="error">
            <#if error??>
                Неверное имя пользователя или пароль
            </#if>
            </div>
                <input type="text" class="form-control" name="username" placeholder="Email address" required autofocus>
                <input type="password" class="form-control" name="password" placeholder="Password" required>
                <button class="btn  btn-success btn-block" type="submit">Sign in</button>
        </form>
    </div>
</div>


</#macro>
<@main title="Login"/>

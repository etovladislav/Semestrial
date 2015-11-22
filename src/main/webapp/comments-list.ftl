<#list comments as comment>
<div class="row" style="margin-left: 30px">
    <div class="header-item">
        <img src="/avatar.jpg?username=${comment.user.login}" class="img-circle" height="20" width="20" alt=""/>
        <a href="/users/${comment.user.login}" style="font-size: 13px;">${comment.user.login}</a>
    <p style="margin-left: 50px;margin-top: 10px;">${comment.text}</p>
    </div>
</div>
<hr>
</#list>
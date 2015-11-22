<#list users as user>
<div class="row album-item">
    <br>

    <div class="header-item">
        <img src="/avatar.jpg?username=${user.login}" class="img-circle" height="50" width="50" alt=""
             style="margin-right: 10px"/>
        <a href="/users/${user.login}" style="font-size:20px">${user.login}</a>
    </div>
</div>
</#list>
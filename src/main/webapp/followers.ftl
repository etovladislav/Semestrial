<#include "main-template.ftl"/>
<#macro m_body>
<div class="container">
    <div class="col-md-8 col-md-offset-2 container-page container-page" style="background-color:#ffffff;padding:40px;">
        <h2>${page}: </h2>

        <div id="all-users-list">
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
        </div>
    </div>
    <!--/stories-->
    <div style="clear: both;"></div>
</div>
</div>
</div>
</div>
</#macro>

<@main title="All users"/>

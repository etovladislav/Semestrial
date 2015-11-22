<#include "main-template.ftl"/>
<#macro m_body>

<div class="container">
    <div class="col-md-8 col-md-offset-2 container-page container-page" style="background-color:#ffffff;padding:40px;">
        <h2>News: </h2>
        <div id="posts-user">
            <#if posts?has_content>
                <#list posts as post>
                    <div class="row album-item">
                        <div class="header-item">
                            <img src="/avatar.jpg?username=${post.sendUser.login}" class="img-circle" height="30" width="30"
                                 alt=""/>
                            <a href="/users/${post.sendUser.login}">${post.sendUser.login}</a>
                        </div>
                        <div class="col-md-10 col-sm-9">
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="post-text">
                                        <p>${post.text}</p>
                                    </div>
                                    <small style="font-family:courier,'new courier'; margin-left:10px;"
                                           class="text-muted">${post.date}</small>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                </#list>
            <#else>
                News aren't present
            </#if>
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

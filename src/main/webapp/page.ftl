<#include "main-template.ftl"/>
<#macro m_body>
<div class="container">
    <div class="row profile">
        <div class="col-md-3" style="padding-bottom: 20px">
            <div class="profile-sidebar">
                <!-- SIDEBAR USERPIC -->
                <div class="profile-userpic">
                    <img src="/avatar.jpg?username=${user.login}"
                         class="img-responsive" alt="">
                </div>
                <!-- END SIDEBAR USERPIC -->
                <!-- SIDEBAR USER TITLE -->
                <div class="profile-usertitle">
                    <div class="profile-usertitle-name">
                    ${user.firstName} ${user.lastName}
                    </div>
                    <div class="profile-usertitle-job" id="username">${user.login}</div>
                </div>
                <#if auth??>
                    <div class="profile-usertitle-job" id="inf-followers">

                    </div>
                </#if>
                <div class="profile-usermenu">
                    <ul class="nav">
                        <li>
                            <a href="#" target="_blank">
                                <i class="fa fa-rss"></i>
                                Posts ${posts} </a>
                        </li>
                    </ul>

                    <ul class="nav">
                        <li>
                            <a href="/all-follower/${user.login}">
                                <i class="fa fa-users"></i>
                                Followers</a>
                        </li>
                    </ul>

                    <ul class="nav">
                        <li>
                            <a href="/subscriptions/${user.login}">
                                <i class="fa fa-bookmark-o"></i>
                                Subscriptions</a>
                        </li>
                    </ul>
                </div>
                <!-- END MENU -->
            </div>
        </div>
        <div class="col-md-9 container-page" style="background-color:#ffffff;padding:20px; min-height:334px;">
            <#if auth??>
                <textarea class="form-control" id="js-post-text" rows="3"
                          style="resize: none; margin-bottom: 10px"></textarea>
                <button class="btn btn-primary pull-right" id="js-sendpost">Send</button>
            </#if>
            <#if auth??>
                <div id="posts-user">

                </div>
            <#else>
                <a href="/registration">Register to see</a>.
            </#if>
        </div>
        <!--/stories-->
        <div style="clear: both;"></div>
    </div>
</div>
</div>
</div>
<script src="/js/script.js"></script>
<script src="/js/follow.js"></script>
<script src="/js/like.js"></script>
</#macro>
<@main title="Main page"/>

<#include "main-template.ftl"/>
<#macro m_body>
<div class="container">
    <div class="col-md-8 col-md-offset-2 container-page container-page" style="background-color:#ffffff;padding:40px;">
        <div class="row">
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
                        <a href="#" onclick="like(${post.id})">
                            <small style="font-family:courier,'new courier'; margin-left:10px;"
                                   class="text-muted">Likes <i class="fa fa-heart" style="color:red;"></i> ${post.like}
                            </small>
                        </a>
                        <a href="#">
                            <small style="font-family:courier,'new courier'; margin-left:10px;"
                                   class="text-muted">${post.date}
                            </small>
                        </a>
                    </div>
                </div>
                <hr>
            </div>
        </div>
        <input type="hidden" id="post_id"value="${post.id}">
        <div id="comments-list">

        </div>
<textarea class="form-control" id="js-post-text" rows="3"
          style="resize: none; margin-bottom: 10px"></textarea>
        <button class="btn btn-primary pull-right" id="js-sendpost" onclick="addComment(${post.id})">Send</button>
    </div>
    <!--/stories-->
    <div style="clear: both;"></div>
</div>
</div>
</div>
</div>
<script src="/js/comment.js"></script>
</#macro>

<@main title="All users"/>

<#if posts?has_content>
    <#list posts as post>
    <div class="row album-item">
        <div class="header-item">
            <img src="/avatar.jpg?username=${post.sendUser.login}" class="img-circle" height="30" width="30" alt=""/>
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
                    <a href="/comment?id_post=${post.id}">
                        <small style="font-family:courier,'new courier'; margin-left:10px;"
                               class="text-muted">Comments <i class="fa fa-comment-o"></i>
                        </small>
                    </a>
                    <a href="#">
                        <small style="font-family:courier,'new courier'; margin-left:10px;"
                               class="text-muted">${post.date}
                        </small>
                    </a>
                    <#if auth??>
                        <#if auth = post.user.login>
                            <button type="button" onclick="deletePost(${post.id})"
                                    class="btn btn-default btn-sm pull-right btn-follow"><i
                                    class="fa fa-trash-o" style="color:red;"></i>
                                Delete post
                            </button>
                        </#if>
                    </#if>
                </div>
            </div>
            <hr>
        </div>
    </div>
    </#list>
<#else>
There is nothing written
</#if>
<#if isMyPage>
    <#if isFollow>
    <button class="btn btn-default btn-block btn-follow" id="unFollow" onclick="unFollow()"><i class="fa fa-remove"></i>
        Unsubscribe
    </button>
    <#else>
    <button class="btn btn-default btn-block btn-follow" id="follow" onclick="follow()"><i class="fa fa-user-plus"></i>
        Follow
    </button>
    </#if>
</#if>
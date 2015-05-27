//分页size
var pagerSize = 6;
//是否显示上下翻
var isShowPreNext=false;
//是否显示上下页
var isShowPreNextPage=true;
/*主函数,用来显示分页 p为当前页,pn为总页数*/
function initPage(p, pn) {
    var pageHTML = "";
    if (p < 1 || p > pn) {
        p = 1;
    }
    var pagerIndex = parseInt((p - 1) / pagerSize);
    //首页
    if (p == 1) {
        pageHTML += showFirst(0, p, pn);
    } else {
        pageHTML += showFirst(1, p, pn);
    }
    //上翻
    if(isShowPreNext){
    if (p - pagerSize < 0) {
        pageHTML += showPre(0, p, pn);
    } else {
        pageHTML += showPre(1, p, pn);
    }}
    
     //上一页
       if(isShowPreNextPage){
    if (p - 1 <= 0) {
        pageHTML += showPrePage(0, p, pn);
    } else {
        pageHTML += showPrePage(1, p, pn);
    }}
    var start = pagerIndex * pagerSize + 1;
    var end = (pn <= (pagerIndex * pagerSize + pagerSize)) ? pn : (pagerIndex * pagerSize + pagerSize);
    //页码
    for (var i = start; i <= end; i++) {
        pageHTML += (i == p) ? pHtml(0, i, pn) : pHtml(1, i, pn);
    }
     //下一页
      if(isShowPreNextPage){
    if (p + 1 > pn) {
        pageHTML += showNextPage(0, p, pn);
    } else {
        pageHTML += showNextPage(1, p, pn);
    }}
    //下翻
      if(isShowPreNext){
    if (pagerIndex * pagerSize + pagerSize > pn) {
        pageHTML += showNext(0, p, pn);
    } else {
        pageHTML += showNext(1, p, pn);
    }
      }
    //尾页
    if (p == pn) {
        pageHTML += showLast(0, p, pn);
    } else {
        pageHTML += showLast(1, p, pn);
    }
    document.getElementById("pageNum").innerHTML = "第" + p + "页/共" + pn + "页";
    document.getElementById("pageNav").innerHTML = pageHTML;
}
//页码
function pHtml(t, p, pn) {
    return (t == 0) ? ("<span class='current'>" + p + "</span>") : "<a href='javascript:goPage(" + p + "," + pn + ")'>" + p + "</a>";
}
//显示上pagerSize页
function showPre(t, p, pn) {
    p = p - pagerSize > 0 ? p - pagerSize : 1;
    return (t == 0) ? ("<span class='disabled'>上翻</span>") : ("<a href='javascript:goPage(" + p + "," + pn + ")'>上翻</a>");
}
//显示下pagerSize页
function showNext(t, p, pn) {
    p = p + pagerSize > pn ? pn : p + pagerSize;
    return (t == 0) ? ("<span class='disabled'>下翻</span>") : ("<a href='javascript:goPage(" + p + "," + pn + ")'>下翻</a>");
}

//显示上1页
function showPrePage(t, p, pn) {
    p = p - 1 > 0 ? p - 1 : 1;
    return (t == 0) ? ("<span class='disabled'>上一页</span>") : ("<a href='javascript:goPage(" + p + "," + pn + ")'>上一页</a>");
}
//显示下1页
function showNextPage(t, p, pn) {
    p = p + 1 > pn ? pn : p + 1;
    return (t == 0) ? ("<span class='disabled'>下一页</span>") : ("<a href='javascript:goPage(" + p + "," + pn + ")'>下一页</a>");
}
//显示首页
function showFirst(t, p, pn) {
    return (t == 0) ? ("<span class='disabled'>首页</span>") : ("<a href='javascript:goPage(" + 1 + "," + pn + ")'>首页</a>");
}
//显示尾页
function showLast(t, p, pn) {
    return (t == 0) ? ("<span class='disabled'>尾页</span>") : ("<a href='javascript:goPage(" + pn + "," + pn + ")'>尾页</a>");
}
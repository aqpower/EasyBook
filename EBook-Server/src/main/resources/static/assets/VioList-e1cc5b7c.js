import{f as $,h as x}from"./admin-6db7a148.js";import{f as w}from"./time-b70c9257.js";import{d as y,s as S,a as j,l as R,k as a,b as A,K as B,f as s,h as D,g as t,F as m,A as C,j as l,t as e,i as L,x as z,_ as F}from"./index-09020506.js";const M={class:"overflow-x-auto"},N={class:"table"},P=t("thead",null,[t("tr",null,[t("th",null,"举报项编号"),t("th",null,"被举报帖子ID"),t("th",null,"举报用户ID"),t("th",null,"举报时间"),t("th",null,"举报原因"),t("th",null,"处理")])],-1),T={class:"flex gap-3"},E=["onClick"],H=["onClick"],K=["onClick"],U=["onClick"],q={class:"flex justify-center mt-3"},G={class:"join"},J=["onClick"],Z=y({__name:"VioList",setup(O){const k=S(F),i=j().user,g=R(),c=a(1),h=a(9),_=a(),v=a(0),V=o=>{c.value=o,b()},b=()=>{$(c.value,h.value).then(o=>{o.code==200&&(v.value=Math.ceil(o.data.count/h.value),_.value=o.data.violation)})};A(()=>{b()});const I=o=>{g.push(`/admin/review/posts/${o}`)},u=(o,p)=>{let r={adminId:i==null?void 0:i.id,type:o,violationId:p};x(r).then(n=>{n.code==200&&k({title:"🥳",content:"处理成功！",btnContent:"1"})})};return(o,p)=>{const r=B("RouterView");return l(),s("div",null,[D(r),t("div",M,[t("table",N,[P,t("tbody",null,[(l(!0),s(m,null,C(_.value,(n,f)=>(l(),s("tr",{class:"hover",key:f},[t("th",null,e(n.id),1),t("th",null,e(n.postId),1),t("th",null,e(n.userId),1),t("th",null,e(L(w)(n.violationTime)),1),t("th",null,e(n.violationReason),1),t("th",T,[t("button",{class:"btn btn-success btn-outline min-h-0 h-8",onClick:d=>I(n.postId)}," 查看帖子详情 ",8,E),t("button",{class:"min-h-0 h-8 btn btn-info btn-outline",onClick:d=>u(1,n.id)}," 删帖 ",8,H),t("button",{class:"min-h-0 h-8 btn btn-warning btn-outline",onClick:d=>u(2,n.id)}," 禁言 ",8,K),t("button",{class:"min-h-0 h-8 btn btn-error btn-outline",onClick:d=>u(3,n.id)}," 封禁 ",8,U)])]))),128))])])]),t("div",q,[t("div",G,[(l(!0),s(m,null,C(v.value,n=>(l(),s("button",{class:z(["join-item btn",{"btn-active":n==c.value}]),key:n,onClick:f=>V(n)},e(n),11,J))),128))])])])}}});export{Z as default};
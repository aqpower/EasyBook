import{M as t}from"./index-09020506.js";const i=a=>t.post("/api/v1/login/admins",a),p=(a,s)=>t.get("/api/v1/login/reports",{params:{page:a,pageSize:s}}),n=()=>t.get("/api/v1/posts/all"),e=()=>t.get("/api/v1/user/avatar"),g=()=>t.get("/api/v1/visit"),c=()=>t.get("/api/v1/count"),r=()=>t.get("/api/v1/posts/today/count"),v=a=>t.post("/api/v1/admin/operations",a),A=a=>t.post("/api/v1/admins",a);export{i as a,r as b,n as c,g as d,e,p as f,c as g,v as h,A as i};
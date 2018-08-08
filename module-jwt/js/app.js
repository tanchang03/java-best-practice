//引入
const jwt= require('jsonwebtoken');
// // const expressJwt = require('express-jwt');
// //
// // //定义签名
// // const secret = 'monkeySecret';
// // //生成token
// // const token = jwt.sign({
// //     name: 123
// // }, secret, {
// //     expiresIn:  360 //秒到期时间
// // });
// //
// // console.info(token);
//
// var x = jwt.verify("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYSIsImV4cCI6MTUzMzcxODkxMCwib3BlbklkIjoiYmIiLCJjcmVhdGVkIjoxNTMzNzE3OTEwNDE3fQ.tfmAZ-DnxHTWn1j8ZnUc-ewEEeLvbJZSlcoJGJid5fxe5MuMDh8rnX8Sa4_eQ-BrtZg3lRp6QZTGwng0yh_9Kw",
//     "monkeySecret");
// console.info(x);
console.info(jwt.decode("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOnsiaWQiOiIzIiwiZGVsZXRlZCI6ZmFsc2UsImNyZWF0ZURhdGUiOjE1MTY4ODQ0NzgwMDAsImxhc3RVcGRhdGVkRGF0ZSI6MTUzMzU0OTM3NTAwMCwidmVyc2lvbiI6IjAiLCJ1c2VybmFtZSI6InRhbmNoYW5nMDEiLCJwYXNzd29yZCI6IkUxMEFEQzM5NDlCQTU5QUJCRTU2RTA1N0YyMEY4ODNFIiwiZW1haWwiOiJ0YW5jaGFuZzAxQHN1bmxhbmRzLmNvbSIsImVtcGxveWVlTmFtZSI6IuiwreeVhSIsImVtcGxveWVlSWQiOiIxIiwib3JnRGVwdElkIjoiNTE4MCYyNjMiLCJkZXB0Ijoi5q2m5rGJ5Lqn5ZOB56CU5Y-R6YOoIiwicGhvbmUiOiIxMzk3MTA2ODY5MyIsImdlbmRlciI6bnVsbCwidGVhY2hlciI6dHJ1ZSwidG9rZW4iOm51bGwsInJvbGVMaXN0IjpbXX0sImNyZWF0ZWQiOjE1MzM3MTg0NDYxNzksImV4cCI6MTUzNDMyMzI0Nn0.Z_ysvae4g5zTrxkGe6YT63KtiIzFy62RVEc8tyU_Svs9YeOZEJYazjh-E6ogrUHtydIeuoaJ-HEsncf1iZnJjw"));
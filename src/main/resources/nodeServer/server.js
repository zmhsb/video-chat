var http = require('http');
var fs = require('fs');
var server = http.createServer(function (request,response){
    response.setHeader('Access-Control-Allow-Origin', '*');
    response.setHeader("Access-Control-Allow-Headers", "film-type,Content-Type,Access-Token");
    response.setHeader("Access-Control-Allow-Methods", "POST");
    requestDispatcher(request,response);
    
})



requestDispatcher = (request,response)=>{
    url = request.url;
    filePatterns = [
        /\/js\/.*/,
        /\/css\/.*/,
        /\/vedio\/.*/
    ]
    var exist = false;
    for (var i = 0;i < filePatterns.length;i++){
        if (filePatterns[i].test(url)){
            exist = true
        }
    }
    if (!exist){

    switch(url){
        case '/': request1(request,response);break;
        case '/upload' : request2(request,response);break;
        case '/index' : request3(request, response);break;
        default : request404(response); break;
    }

    }
    else{
        responseRender(response,url);
    }

}
responseWriter = (response,data) =>{
    response.writeHead('200',{'Content-Type':'application/json;charset=UTF-8'})
    response.write(JSON.stringify(data));
    response.end();
}
responseRender = (response,filename) => {
    response.writeHead('200',{'Content-Tpye':'text/html'})
    fs.readFile('..'+filename,'utf-8',function(err,data){
        if (err){
            throw err;
        }
        else{
            response.end(data);
        }
    })
}

request404 = (response)=>{
    response.writeHead('404');
    response.end();
}

request1 = (request,response)=>{   //  '/'
    

}
request2 = (request,response)=>{ // '/upload'


    if (request.method == 'OPTIONS'){
        response.end()
    }
    else if (request.method == 'POST'){
        var film_content = []
        request.on('data',(chunk)=>{
            film_content.push(chunk);
            // film_content = Buffer.concat([film_content,chunk] , film_content.length+chunk.length);
        })



        
        request.on('end', () => {
            var film_type = request.headers['film-type'];
            film_content = Buffer.concat(film_content);
            if (film_type){
                var film_name = new Date().getTime();
                var src = '/video/'+(film_name).toString()+'.'+film_type
                fs.writeFile('../static' + src, film_content, (err)=>{
                    if (err) throw err;
                    console.log(film_name+"已保存");
                })

                responseWriter(response,{
                    'status':'ok',
                    'save':'success',
                    // 'film_name':film_name,
                    'src':src
                })
            }
            else{
                responseWriter(response,{
                    'status':'ok',
                    'save':'fail'
                })
            }
        })
        
        
    }
    else{
        responseRender(response, '/view/upload.html');
    }
}

request3 = (request,response) =>{
    responseRender(response, '/view/index.html');
    
}








server.listen('49999' ,function(){
    console.log("Server Start!");
    
});


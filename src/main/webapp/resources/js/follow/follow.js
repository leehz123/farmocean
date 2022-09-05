const btn1 = document.getElementById('btn-xhttp');
const out = document.getElementById('out');

// AJAX

//1. Ajax를 위한 xhttp 인스턴스 생성

const xhttp = new XMLHttpRequest();

//2. xhttp에 이벤트 설정

xhttp.addEventListener('readystatechange', (e) =>{
    //console.log(e);
    const readyState = e.target.readyState;
    const responseText = e.target.responseText;

    if(readyState == 1){
        console.log('서버로 비동기 요청을 보냈다');
    } else if(readyState == 2){
        console.log('서버가 내 요청을 받았다');
    } else if (readyState == 3){
        console.log('서버가 내 요청에 대한 처리를 시작했다');
    } else if (readyState == 4 ){
        console.log('서버의 처리가 끝나고 내 요청에 대한 응답이 도착했다');
        console.log('응답은 responseText에 들어왔다', responseText);

        //5. JSON 문자열 -> Javascript Object로 변환
        const pizza = JSON.parse(responseText);

        console.log('pizza name: ', pizza.name);
        console.log('pizza calories: ', pizza.calories);

        const pizzaDiv = document.createElement('div');

        pizzaDiv.innerText = (`${pizza.name}/${pizza.calories}/${pizza.price}`);

        out.appendChild(pizzaDiv);

    } else {
        console.log('??', readyState, responseText);
    }
});

const select = document.getElementById('select');

btn1.addEventListener('click', (e) =>{
    //3. 새로운 xhttp 연결을 생성 open(method, url)
    xhttp.open('GET', '/restful/sample/pizza/2');

    //4. 원하는 타이밍에 요청을 전송
    xhttp.send();
});
const xhttp2 = new XMLHttpRequest();

var app = (function() {  
    xhttp2.open('GET', '/restful/pizzalist');
    xhttp2.send();
}());


xhttp2.addEventListener('readystatechange', (e) =>{

    const readyState2 = e.target.readyState;
    const responseText2 = e.target.responseText;



    console.log(e);
    console.log(readyState2);
    console.log(responseText2);
    if (readyState2 == 4 ){
        const pizzas = JSON.parse(responseText2);

        console.log(pizzas);
        for(var i = 0; i < pizzas.length; i ++){
            var optionValue = pizzas[i].id;
            var optionName = pizzas[i].name;
            console.log('name: ',optionName);

            const opt = document.createElement("option");
            opt.value = optionValue;
            opt.text = optionName;
            select.append(opt);
        }
      
    }
});

const xhttp3 = new XMLHttpRequest();


const showValue = (target) => {


    xhttp3.open('GET', `/restful/pizza/${target.value}`);
    xhttp3.send();
    
    // 선택한 option의 value 값
    console.log(target.value);
    
    // option의 text 값
    console.log(target.options[target.selectedIndex].text);
  }

  xhttp3.addEventListener('readystatechange', (e) =>{

    const readyState3 = e.target.readyState;
    const responseText3 = e.target.responseText;

    const price = document.getElementById("price");
    const calories = document.getElementById("calories");
    const id = document.getElementById("id");

    if (readyState3 == 4 ){
        const pizza = JSON.parse(responseText3);
        console.log(pizza);
        price.value = `${pizza.price}`;
        calories.value = `${pizza.calories}`;
        id.value = `${pizza.id}`;

    }
});

const btn2 = document.getElementById('btn-put');
const xhttp4 = new XMLHttpRequest();

const price = document.getElementById("price");
const calories = document.getElementById("calories");
const id = document.getElementById("id");

btn2.addEventListener('click', (e) =>{
    //3. 새로운 xhttp 연결을 생성 open(method, url)


    const id = document.getElementById("id");
    console.log(`${id.value}`);
    xhttp4.open('GET', `/restful/pizza/${id.value}`);

    xhttp4.send();
    
});

const xhttp5 = new XMLHttpRequest();

 xhttp4.addEventListener('readystatechange', (e) =>{

     const readyState4 = e.target.readyState;
     const responseText4 = e.target.responseText;

    if (readyState4 == 4 ){
    
    
        console.log(responseText4)

        const pizza = JSON.parse(responseText4);
        console.log('pizza : ' ,pizza);

        const temp = `{"id":${id.value},"name":"${pizza.name}","price":${price.value},"calories":${calories.value}}`;
        console.log('temp : ',temp);
        console.log(temp);

        const newpizza = JSON.parse(temp);
        console.log('newpizza : ',newpizza);
		
		const pizza2={
			id: id.value,
			name: pizza.name,
			price: price.value,
			calories: calories.value 
		};
		
		console.log(pizza2);
		// GET 방식은 주소 뒤에 ?name=value&.. 로 실어 보내면 되지만
		// 그외 방식은 send() 메서드에 데이터를 실어 보낸다
        xhttp5.open('PUT', `/restful/pizza/update`);
        
        // xhr 요청 헤더 설정 (JSON 형식으로 간다고 서버에 알려야 한다)
        xhttp5.setRequestHeader('content-type', 'application/json;charset=UTF-8');
        //xhttp5.send(newpizza);
        xhttp5.send(JSON.stringify(pizza2));


    }
});

const out2 = document.getElementById('out2');

 xhttp5.addEventListener('readystatechange', (e) =>{

     const readyState5 = e.target.readyState;
 

    if (readyState5 == 4 ){
   			const responseText5 = e.target.responseText;
    	console.log(responseText5);
    	if(responseText5 == 1){
    		out2.innerText = 'Successs';
    		out2.style.color = 'green';
    		app();
    		
    	} else{
    		out2.innerText = 'Fail';
    		out2.style.color = 'red';
    	}


    }
});

const btn3 = document.getElementById('btn-post');
const xhttp6 = new XMLHttpRequest();

const postPrice = document.getElementById("post-price");
const postCalories = document.getElementById("post-calories");
const postName = document.getElementById("post-name");

btn3.addEventListener('click', (e) =>{
    
    const pizza3={
			name: postName.value,
			price: postPrice.value,
			calories: postCalories.value 
		};
    
    
    console.log(pizza3);
    xhttp6.open('POST', `/restful/pizza/add2`);

    xhttp6.setRequestHeader('content-type', 'application/json;charset=UTF-8');

    xhttp6.send(JSON.stringify(pizza3));
    
});

const out3 = document.getElementById('out3');

 xhttp6.addEventListener('readystatechange', (e) =>{

     const readyState6 = e.target.readyState;
 
    if (readyState6 == 4 ){
   		const responseText6 = e.target.responseText;
    	console.log(responseText6);
    	if(responseText6 == 1){
    		out3.innerText = 'Successs';
    		out3.style.color = 'green';
    		
    	} else{
    		out3.innerText = 'Fail';
    		out3.style.color = 'red';
    	}


    }
});


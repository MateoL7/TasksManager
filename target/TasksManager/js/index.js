const description = document.getElementById('description');
const regBt = document.getElementById('regBt');
const toDoContainer = document.getElementById('toDoContainer');
const doingContainer = document.getElementById('doingContainer');
const doneContainer = document.getElementById('doneContainer');

const register = () =>{
    let obj = {
        id: 0,
        description: description.value
    };
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState == 4){
            console.log(xhr.responseText);
            getAllToDo();
            getAllDoing();
            getAllDone();
        }
    });
    xhr.open('POST', 'http://localhost:8080/TasksManager/api/toDoTask/create');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(obj));
};

regBt.addEventListener('click', register);

const getAllToDo = () =>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log(response);
            toDoContainer.innerHTML = '';
            for(let i = 0; i<response.length;i++){
                let toDoDTO = response[i];
                let view = new ToDoView(toDoDTO);
                view.onDeleteFinish = () =>{
                    toDoContainer.removeChild(document.getElementById('toDoTask'+toDoDTO.id));
                };
                view.onForwardFinish = () => {
                   getAllDoing();
                };
                toDoContainer.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/TasksManager/api/toDoTask/all');
    xhr.send();
};
getAllToDo();
const getAllDoing = () =>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log('DOING NEXT')
            console.log(response);
            doingContainer.innerHTML = '';
            for(let i = 0; i<response.length;i++){
                let doingDTO = response[i];
                let view = new DoingView(doingDTO);
                view.onDeleteFinish = () =>{
                    doingContainer.removeChild(document.getElementById('doingTask'+doingDTO.id))
                };
                view.onForwardFinish = () => {
                    getAllDone();
                 };
                view.onBackwardFinish = () => {
                    getAllToDo();
                 };
                doingContainer.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/TasksManager/api/doingTask/all');
    xhr.send();
};
getAllDoing();
const getAllDone = () =>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log('DONE NEXT')
            console.log(response);
            doneContainer.innerHTML = '';
            for(let i = 0; i<response.length;i++){
                let doneDTO = response[i];
                let view = new DoneView(doneDTO);
                view.onDeleteFinish = () =>{
                    doneContainer.removeChild(document.getElementById('doneTask'+doneDTO.id))
                };
                view.onBackwardFinish = () => {
                    getAllDoing();
                 };
                doneContainer.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/TasksManager/api/doneTask/all');
    xhr.send();
};
getAllDone();
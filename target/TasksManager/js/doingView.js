class DoingView{
    constructor(task){
        this.task = task;
        this.onDeleteFinish = null;
        this.onForwardFinish = null;
        this.onBackwardFinish = null;
    }
    deleteTask = () => {
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                console.log(xhr.responseText);
                if(this.onDeleteFinish!=null){
                    this.onDeleteFinish();
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/TasksManager/api/doingTask/delete/'+this.task.id);
        xhr.send();
    }
    nextState = () =>{
        let obj = {
            id: 0,
            description: this.task.description
        };
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                console.log(xhr.responseText);
                this.deleteTask();
                if(this.onForwardFinish!=null){
                    this.onForwardFinish();
                }
            }
        });
        xhr.open('POST', 'http://localhost:8080/TasksManager/api/doneTask/create');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(obj));
    }
    prevState = () =>{
        let obj = {
            id: 0,
            description: this.task.description
        };
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                console.log(xhr.responseText);
                this.deleteTask();
                if(this.onBackwardFinish!=null){
                    this.onBackwardFinish();
                }
            }
        });
        xhr.open('POST', 'http://localhost:8080/TasksManager/api/toDoTask/create');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(obj));
    }

    render = () =>{
        let component = document.createElement('div');
        component.id = 'doingTask' + this.task.id;
        component.className = 'doingComponent';
        let description = document.createElement('p');
        let date = document.createElement('small');
        let nextBt = document.createElement('button');
        let prevBt = document.createElement('button');
        let deleteBt = document.createElement('button');

        deleteBt.className = 'deleteBt';
        deleteBt.innerHTML = ' ';
        prevBt.className = 'prevBt';
        prevBt.innerHTML = ' ';
        nextBt.className = 'nextBt';
        nextBt.innerHTML = ' ';
        description.innerHTML = this.task.description;
        date.innerHTML = this.task.date;

        component.appendChild(description);
        component.appendChild(date);
        component.appendChild(nextBt);
        component.appendChild(deleteBt);
        component.appendChild(prevBt);

        deleteBt.addEventListener('click', this.deleteTask);
        nextBt.addEventListener('click',this.nextState);
        prevBt.addEventListener('click',this.prevState);

        return component;
    }
}
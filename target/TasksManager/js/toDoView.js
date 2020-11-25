class ToDoView{

    constructor(task){
        this.task = task;
        this.onDeleteFinish = null;
        this.onForwardFinish = null;
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
        xhr.open('DELETE', 'http://localhost:8080/TasksManager/api/toDoTask/delete/'+this.task.id);
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
        xhr.open('POST', 'http://localhost:8080/TasksManager/api/doingTask/create');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(obj));
    }

    render = () =>{
        let component = document.createElement('div');
        component.id = 'toDoTask' + this.task.id;
        component.className = 'toDoComponent';
        let description = document.createElement('p');
        let date = document.createElement('small');
        let nextBt = document.createElement('button');
        let deleteBt = document.createElement('button');

        deleteBt.className = 'deleteBt';
        deleteBt.innerHTML = ' ';
        nextBt.className = 'nextBt';
        nextBt.innerHTML = ' ';
        description.innerHTML = this.task.description;
        date.innerHTML = this.task.date;

        component.appendChild(description);
        component.appendChild(date);
        component.appendChild(nextBt);
        component.appendChild(deleteBt);

        deleteBt.addEventListener('click', this.deleteTask);
        nextBt.addEventListener('click', this.nextState);

       // nextBt.addEventListener('click',this.nextState);

        return component;
    }

}
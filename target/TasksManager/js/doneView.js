class DoneView{
    constructor(task){
        this.task = task;
        this.onDeleteFinish = null;
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
        xhr.open('DELETE', 'http://localhost:8080/TasksManager/api/doneTask/delete/'+this.task.id);
        xhr.send();
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
        xhr.open('POST', 'http://localhost:8080/TasksManager/api/doingTask/create');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(obj));
    }

    render = () =>{
        let component = document.createElement('div');
        component.id = 'doneTask' + this.task.id;
        component.className = 'doneComponent';
        let description = document.createElement('p');
        let date = document.createElement('small');
        let deleteBt = document.createElement('button');
        let prevBt = document.createElement('button');

        prevBt.className = 'prevBt';
        prevBt.innerHTML = ' ';
        deleteBt.className = 'deleteBt';
        deleteBt.innerHTML = ' ';
        description.innerHTML = this.task.description;
        date.innerHTML = this.task.date;

        component.appendChild(description);
        component.appendChild(date);
        component.appendChild(deleteBt);
        component.appendChild(prevBt);

        deleteBt.addEventListener('click', this.deleteTask);
        prevBt.addEventListener('click',this.prevState);

        return component;
    }
}
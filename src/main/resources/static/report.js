    function reporting(id, startDate, endDate)
    {
        const ID = id;//document.getElementById("employeeID").value;
        const start = startDate; //document.getElementById("startDate").value;
        const end = endDate;//document.getElementById("endDate").value;
        const address = 'http://localhost:8080/send/?workerID='+ID+'&startDate='+start+'&endDate='+end;
        console.log(ID);
        console.log(start);
        console.log(end);
        console.log(address);
        fetch(address)
            .then(resp => resp.blob())
            .then(blob => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = url;
                a.download = 'reportForWorker.json';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
                alert('Ваш отчёт сохранился!');
            })
            .catch(() => alert('Что-то пошло не так...'));

    };
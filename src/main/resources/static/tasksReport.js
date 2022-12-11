function tasksReport()
{
    fetch('http://localhost:8080/tasks-report')
        .then(resp => resp.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'tasksReport.json';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            alert('Ваш отчёт сохранился!');
        })
        .catch(() => alert('Что-то пошло не так...'));

};
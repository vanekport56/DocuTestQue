<!DOCTYPE html>
<html>
<head>
    <title>Редактирование документа</title>
    <link rel="stylesheet" type="text/css" href="/css/styleInfo.css">

</head>
<body>
<div class="container">
    <h3>${document.title} №${document.id}</h3>
    <div class="info-form">
         <form action="/document/update/${document.id}" method="post">
            <label>Содержание:</label>
            <input type="text" class="form-input" id="description" name="description" value="${document.description}"required/>

            <input type="hidden" id="title" name="title" value="${document.title}"required>

             <label>Сумма:</label>
            <input type="text" class="form-input" id="price" name="price" value="${document.price}"required/>

             <label>Автор:</label>
            <input type="text" class="form-input" id="author" name="author" value="${document.author}"required/>

            <input type="submit" value="Сохранить изменения" class="button"/>
             <input type="button" value="Удалить документ" class="button" onclick="confirmDelete()"/>
        </div>
    </form>
    <button onclick="window.history.back()" class="button">Назад</button>
    </div>

    <br><br>

<script>
    function confirmDelete() {
        if (confirm("Вы уверены, что хотите удалить этот документ?")) {
            deleteDocument();
        }
    }

    function deleteDocument() {
        var h3 = document.querySelector('h3').textContent;
        var id = h3.split('№')[1];  // Получаю id из текста элемента, который следует за №
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/document/delete/" + id, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onload = function () {
            // Обработка ответа сервера здесь
            if (xhr.status == 200) {
                // Если сервер возвращает статус 200 (ОК), то перенаправляем пользователя на главную страницу
                window.location.href = "/";
            } else {
                // Если сервер возвращает любой другой статус, выводим сообщение об ошибке
                alert("Произошла ошибка при удалении документа. Пожалуйста, попробуйте ещё раз.");
            }
        };
        // Отправляем запрос с данными метода
        xhr.send("_method=delete");
    }
</script>
</body>
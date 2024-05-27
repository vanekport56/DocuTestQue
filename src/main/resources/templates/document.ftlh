<!DOCTYPE html>
<html>
<head>
    <title>Документы</title>

    <link rel="stylesheet" type="text/css" href="/css/styleDocs.css">

    <script>

        document.addEventListener('DOMContentLoaded', (event) => {
            // Парсим строку запроса из текущего URL
            const queryParams = new URLSearchParams(window.location.search);
            // Получаем параметр тайтл из строки запроса
            const title = queryParams.get('title');
            if (title) {
                // Находим select элемент по его id и устанавливаем значение
                const selectElement = document.getElementById('title');
                selectElement.value = title;
            }
        });


        function redirectToDocument(id) {
            window.location.href = "/document/" + id;
        }

        function submitForm() {
            document.getElementById("filter-form").submit();
        }

    </script>

</head>
<body>


<div class="container">
    <h1>Документы</h1>
    <hr>
    <div class="search-form">
        <form action="/document/search" method="get">
            <input type="submit" value="Найти по номеру"> <input type="number" name="id" required>
        </form>
        <br>

        <form id="filter-form" action="/">
            <label for="title">Фильтр:</label>
            <select name="title" id="title" onchange="submitForm()">
                <option value="">Все документы</option>
                <option value="Реализация товаров">Реализация товаров</option>
                <option value="Реализация услуг">Реализация услуг</option>
                <option value="Реализация товаров и услуг">Реализация товаров и услуг</option>
            </select>
        </form>

    </div>

    <table class="table">
        <thead>
        <tr>
            <th class="first-column">Тип документа</th>
            <th class="second-column">Номер</th>
        </tr>
        </thead>
        <tbody>
        <#list documents as document>
            <tr onclick="redirectToDocument(${document.id})">
                <td class="first-column">${document.title}</td>
                <td class="second-column">${document.id}</td>
            </tr>
        <#else>
            <tr>
                <td colspan="2">
                    <h3>Документов нет</h3>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <div class="create-button">
        <a href="/document/createForm" class="button">Создать новый документ</a>
    </div>
</div>
</body>
</html>
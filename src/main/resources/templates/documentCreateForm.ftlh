<!DOCTYPE html>
<html>
<head>
    <title>Документы</title>
    <link rel="stylesheet" type="text/css" href="/css/styleCreateForm.css">
</head>
<body>
<div class="container">
    <h3>Новый документ</h3>
    <div class="create-form">
        <form action="/document/create" method="post">
            <label>Тип документа:</label>
            <select name="title" class="form-input" required>
                <option value="Реализация товаров и услуг">Реализация товаров и услуг</option>
                <option value="Реализация товаров">Реализация товаров</option>
                <option value="Реализация услуг">Реализация услуг</option>
            </select>

            <label>Номер:</label>
            <input type="number" name="id" class="form-input" placeholder="Номер документа должен быть уникален"required/>

            <label>Содержание:</label>
            <input type="text" name="description" class="form-input" required/>

            <label>Сумма:</label>
            <input type="number" name="price" class="form-input" required/>

            <label>Автор:</label>
            <input type="text" name="author" class="form-input" required/>

            <input type="submit" value="Создать документ" class="button"/>
            <input type="hidden" name="documentType" value=""/>

        </form><button onclick="window.history.back()" class="button">Назад</button>
<#--        Тут хотел отработать ошибку, которая возникалла бы на форме при сохранении документа с существующим id, но не успел, подметать такое нет времени, сдаю как есть-->
        <#if (error)??>
            <br>

            <div style="color: red;">${error}</div>
        </#if>
    </div>

</div>
<script type="text/javascript">
    //этот скрипт для того чтобы java на стороне сервака уже определила к какому типу отнести созданный документ
    document.addEventListener('DOMContentLoaded', function () {
        document.querySelector('select[name="title"]').addEventListener('change', function () {
            var inputType = document.querySelector('input[name="documentType"]');
            if (this.value === 'Реализация услуг') {
                inputType.value = 'services';
            } else {
                inputType.value = 'products';
            }
        });
    });


</script>
</body>
</html>
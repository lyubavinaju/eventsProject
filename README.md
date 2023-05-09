# eventsProject

API:<br>
<b>user/register</b> - регистрация пользователя<br>
параметры: username, pwd <br>
пример: http://localhost:8080/user/register?username=un1111111&pwd=pass2 <br>
<b>event/save</b> - сохранение события <br>
параметры: name - название события, необязательные: username, pwd - для авторизации <br>
<b>event/stats</b> - получение статистики <br>
параметры: date, name - дата и название события <br>
пример: http://localhost:8080/event/stats?date=2023-05-09&name=event1 <br>
<p>Также реализовано несколько простых тестов. </p>
Для работы приложения требуется postgresql, поменять application.properties, и выполненить команду create extension pgcrypto;


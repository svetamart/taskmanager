## Приложение Task Manager для управления задачами
Эндпойнты:
* GET /tasks - получить список всех задач
* GET /tasks/id/{id} - получить задачу по ID 
* POST /tasks/add - добавить новую задачу
* POST /tasks/generate - генерировать новую случайную задачу 
* PUT /tasks/{id}/executors/{executorId} - назначить исполнителя для задачи
* PUT /tasks/{id}/{status} - обновить статус задачи 
* DELETE /tasks/{id} - удалить задачу 
* GET /tasks/status/{status} - получить список задач по статусу 
* GET /executors - получить список всех исполнителей 
* GET /executors/id/{id} - получить исполнителя по ID 
* POST /executors - добавить нового исполнителя 
* GET /executors/name/{name} - получить исполнителя по имени
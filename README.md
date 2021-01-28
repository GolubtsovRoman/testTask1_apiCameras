#### testTask1_apiCameras 

Текст задачи описан в файле "task.pdf" (лежит в корне проекта).

Чтобы проверить работу необходимо:
1. запустить проект
2. на адрес http://localhost:8080/api/get-info-by-url (если проект запускается локально) необходисо сделать post запрос. Входа по логину и паролю нет.
3. придет ожидаемый ответ
 
Запрос на вход должен быть в виде json

    {
        "url": "http://www.mocky.io/v2/5c51b9dd3400003252129fb5"
    }

Здесь "url" это сслыка на json, который содержит иходные данные. Он должен выглядеть <a href="http://www.mocky.io/v2/5c51b9dd3400003252129fb5">примерно так</a>:

    [
        {
            "id": 1,
            "sourceDataUrl": "http://www.mocky.io/v2/5c51b230340000094f129f5d",
            "tokenDataUrl": "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s"
        },
        {
            "id": 20,
            "sourceDataUrl": "http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms",
            "tokenDataUrl": "http://www.mocky.io/v2/5c51b5ed340000554e129f7e"
        }
    ]

Каждая ссылка в json'е должна нести в себе данные следующего вида:
Для поля "sourceDataUrl"

    {
        "urlType": "LIVE",
        "videoUrl": "rtsp://127.0.0.1/1"
    }

Для поля "tokenDataUrl"

    {
        "value": "fa4b588e-249b-11e9-ab14-d663bd873d93",
        "ttl": 120
    }

В файле "task.pdf" есть более подробное описание.

Данные по камерам вернутся в агрегированном виде (они будут кпорядочены по "id"):
    
    [
        {
            "id": 1,
            "urlType": "LIVE",
            "videoUrl": "rtsp://127.0.0.1/1",
            "value": "fa4b588e-249b-11e9-ab14-d663bd873d93",
            "ttl": "120"
        },
        {
            "id": 20,
            "urlType": "ARCHIVE",
            "videoUrl": "rtsp://127.0.0.1/2",
            "value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",
            "ttl": "60"
        }
    ]   
    


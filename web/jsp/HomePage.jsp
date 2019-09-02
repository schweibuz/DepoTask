<%--
  Created by IntelliJ IDEA.
  User: andmat
  Date: 2019-08-18
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Home Page</title>
    <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="<c:url value="/resources/validate.js"/>"></script>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=<ваш API-ключ>&lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript" src="https://rawgit.com/anhr/InputKeyFilter/master/Common.js"></script>
    <script type="text/javascript" src="https://rawgit.com/anhr/InputKeyFilter/master/InputKeyFilter.js"></script>
    <script src="jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        var myMap;

        ymaps.ready(function () {
            coordinates = [];

            myMap = new ymaps.Map("map", {
                center: [59.939095, 30.315868],
                zoom: 10,
                behaviors: ['default', 'scrollZoom']
            }, {
                searchControlProvider: 'yandex#search'
            }),
                clusterer = new ymaps.Clusterer({
                    preset: 'islands#invertedVioletClusterIcons',
                    groupByCoordinates: false,
                    clusterDisableClickZoom: true,
                    clusterHideIconOnBalloonOpen: false,
                    geoObjectHideIconOnBalloonOpen: false
                }),
                getPointData = function (index) {
                    return {
                        // balloonContentHeader: '<font size=3><b><a target="_blank" href="https://yandex.ru">Здесь может быть ваша ссылка</a></b></font>',
                        // balloonContentBody: '<p>Ваше имя: <input name="login"></p><p>Телефон в формате 2xxx-xxx:  <input></p><p><input type="submit" value="Отправить"></p>',
                        // balloonContentFooter: '<font size=1>Информация предоставлена: </font> балуном <strong>метки ' + index + '</strong>',
                        clusterCaption: 'метка <strong>' + index + '</strong>'
                    };
                },
                getPointOptions = function () {
                    return {
                        preset: 'islands#violetIcon'
                    };
                },
                points = new Array();
            <c:forEach items="${requestScope.transport}" var="transport">
            points.push([${transport.length}, ${transport.width}]);
            </c:forEach>

            geoObjects = [];

            for (var i = 0; i < points.length; i++) {
                geoObjects[i] = new ymaps.Placemark(points[i], getPointData(i), getPointOptions());
            }

            clusterer.options.set({
                gridSize: 80,
                clusterDisableClickZoom: true
            });

            clusterer.add(geoObjects);
            myMap.geoObjects.add(clusterer);

            myMap.setBounds(clusterer.getBounds(), {
                checkZoomRange: true
            });
        });
    </script>
    <h1>Горэлектротранс (Санкт-Петербург)</h1>
</head>
<body>
<h1></h1>
<div class="tabs">
    <input type="radio" name="inset" value="" id="tab_1" checked>
    <label for="tab_1">Состав</label>

    <input type="radio" name="inset" value="" id="tab_2">
    <label for="tab_2">Парк</label>

    <input type="radio" name="inset" value="" id="tab_3">
    <label for="tab_3">Редактировать</label>

    <div id="txt_1">
        <p>Добавить состав</p>
        <div>
            <form name="transport" action="add_transport" method="post" onsubmit="return validateForm()">

                <%--                <script type="javascript">--%>
                <%--                    var arr = new Array();--%>
                <%--                    <c:if test="${empty requestScope.transport}">--%>
                <%--                    arr.push("No customers Found");--%>
                <%--                    </c:if>--%>
                <%--                    <c:forEach items="${requestScope.transport}" var="transport">--%>
                <%--                    &lt;%&ndash;                    <option value="<c:out value="${transport.transport_id}"/>"></option>&ndash;%&gt;--%>
                <%--                    &lt;%&ndash;                    <c:set var="alphabet" value="${[transport.number]}" scope="application"/>&ndash;%&gt;--%>
                <%--                    arr.push("${transport.number}");--%>
                <%--                    </c:forEach>--%>
                <%--                    alert("SDF" + arr[0]);--%>

                <%--                    var arr = new Array();--%>
                <%--                    <c:forEach items="${requestScope.transport}" var="transport">--%>
                <%--                    arr.push([${transport.number}]);--%>
                <%--                    </c:forEach>--%>
                <%--                </script>--%>

                <label for="kind">Вид транспорта</label>
                <select id="kind" name="kind">
                    <c:forEach items="${requestScope.kind}" var="kind">
                        <option value="<c:out value="${kind.kind}"/>"><c:out value="${kind.kind}"/></option>
                    </c:forEach>
                </select>

                <label for="type">Тип состава</label>
                <select id="type" name="type">
                    <option value="Пассажирский">Пассажирский</option>
                    <option value="Грузовой">Грузовой</option>
                </select>

                <label class="checkbox">Служебный
                    <input type="checkbox" id="check" name="service" value="Служебный">
                    <span class="checkbox__text"></span>
                </label>

                <br><br>

                <label for="park">Парк</label>
                <select id="park" name="park">
                    <c:forEach items="${requestScope.park}" var="park">
                        <option value="<c:out value="${park.park_number}"/>"><c:out
                                value="${park.park_number}, ${park.park_type}"/></option>
                    </c:forEach>
                </select>

                <label for="number">Номер состава</label>
                <input type="text" id="number" name="number" placeholder="Введите номер состава.."/>
                <script>CreateNumberFilterMy("number")</script>

                <label for="capacity">Вместимость/Грузоподъёмность</label>
                <input type="text" id="capacity" name="capacity"
                       placeholder="Введите вместимость(человек) или грузоподъёмность(килограмм).."/>
                <script>CreateDigitFilterMy("capacity")</script>

                <label for="width">Широта</label>
                <input type="text" id="width" name="width"
                       placeholder="Введите широту в пределах от 59.813756 до 60.081968.."/>
                <script>CreateFloatFilterMy("width")</script>

                <label for="length">Долгота</label>
                <input type="text" id="length" name="length"
                       placeholder="Введите долготу в пределах от 30.208282 до 30.528259.."/>
                <script>CreateFloatFilterMy("length")</script>

                <input type="submit" name="registration" value="Добавить">
<%--                <input type="button" value="Выйти" onclick="javascript:window.location='/WebApp/'"/>--%>
            </form>
            <br>
            <table style="background-color: white;" WIDTH="30%" border="1">
                <tr>
                    <th>Номер</th>
                    <th>Занесён в базу</th>
                    <th>Дата изменения</th>
                    <th>Вид</th>
                    <th>Номер парка</th>
                    <th>Парк</th>
                    <th>Тип</th>
                    <th>Вместимость/Грузоподъёмность</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${requestScope.transport}" var="transport">
                    <tr>
                        <td><c:out value="${transport.number}"/></td>
                        <td><c:out value="${transport.created_at}"/></td>
                        <td><c:out value="${transport.modified_at}"/></td>
                        <td><c:out value="${transport.kind}"/></td>
                        <td><c:out value="${transport.park_number}"/></td>
                        <td><c:out value="${transport.park_type}"/></td>
                        <td><c:out value="${transport.transport_type}"/></td>
                        <td><c:out value="${transport.capacity}"/></td>
                        <td><a href="delete?transport_id=<c:out value="${transport.transport_id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <div id="map" style="width: 80%; height: 400px"></div>
        </div>
    </div>
    <div id="txt_2">
        <p>Добавить парк</p>
        <div>
            <form name="pakr" action="add_park" method="post">

                <label for="park_number">Номер парка</label>
                <input type="text" id="park_number" name="park_number" placeholder="Введите номер парка..">
                <script>CreateParkNumberFilterMy("park_number")</script>

                <label for="park_type">Тип парка</label>
                <select id="park_type" name="park_type">
                    <option value="Трамвайный">Трамвайный</option>
                    <option value="Троллейбусный">Троллейбусный</option>
                    <option value="Совмещённый">Совмещённый</option>
                </select>

                <input type="submit" name="addpark" value="Добавить">
            </form>
        </div>
    </div>
    <div id="txt_3">
        <p>Редактировать парк</p>
        <div>
            <form name="edit" action="update" method="post">

                <label for="edited_park_number">Текущий номер</label>
                <select id="edited_park_number" name="edited_park_number">
                    <c:forEach items="${requestScope.park}" var="park">
                        <option value="<c:out value="${park.park_number}"/>"><c:out
                                value="${park.park_number}"/></option>
                    </c:forEach>
                </select>

                <label for="current_park_number">Новый номер</label>
                <input type="text" id="current_park_number" name="current_park_number"
                       placeholder="Введите номер парка..">
                <script>CreateParkNumberFilterMy("current_park_number")</script>

                <label for="edited_park_type">Тип парка</label>
                <select id="edited_park_type" name="edited_park_type">
                    <option value="Трамвайный">Трамвайный</option>
                    <option value="Троллейбусный">Троллейбусный</option>
                    <option value="Совмещённый">Совмещённый</option>
                </select>

                <input type="submit" name="edit" value="Обновить данные">
            </form>

            <br>
            <table style="background-color: white;" WIDTH="30%" border="1">
                <tr>
                    <th>Номер состава</th>
                    <th>Номер парка</th>
                    <th>Парк</th>
                </tr>
                <c:forEach items="${requestScope.transport}" var="transport">
                    <tr>
                        <td><c:out value="${transport.number}"/></td>
                        <td><c:out value="${transport.park_number}"/></td>
                        <td><c:out value="${transport.park_type}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
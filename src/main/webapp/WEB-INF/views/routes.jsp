<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Маршруты — Менеджер Маршрутов</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="brand-bar">
        <div class="mark">Route<span>//</span>Manager</div>
        <a class="logout" href="${pageContext.request.contextPath}/logout">Выйти</a>
    </div>

    <div class="page">
        <span class="kicker">Пользователь: ${currentUser}</span>
        <h1 class="giant-title">Ваши<br>маршруты<span class="accent">.</span></h1>

        <div class="section-label">Таблица маршрутов</div>
        <div class="route-table-wrap">
            <table class="routes">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Координаты</th>
                    <th>Откуда</th>
                    <th>Куда</th>
                    <th>Дистанция</th>
                    <th>Владелец</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="route" items="${routes}">
                    <tr>
                        <td><span class="route-id">#${route.id}</span></td>
                        <td><span class="route-name">${route.name}</span></td>
                        <td>
                            <span class="coord-tag">
                                <span><span class="axis">X</span>${route.coordinates.x}</span>
                                <span><span class="axis">Y</span>${route.coordinates.y}</span>
                            </span>
                        </td>
                        <td>
                            <span class="coord-tag">
                                <span><span class="axis">X</span>${route.from.x}</span>
                                <span><span class="axis">Y</span>${route.from.y}</span>
                                <span><span class="axis">Z</span>${route.from.z}</span>
                            </span>
                        </td>
                        <td>
                            <span class="coord-tag">
                                <span><span class="axis">X</span>${route.to.x}</span>
                                <span><span class="axis">Y</span>${route.to.y}</span>
                                <span><span class="axis">Z</span>${route.to.z}</span>
                            </span>
                        </td>
                        <td><span class="distance-val">${route.distance}</span></td>
                        <td>${route.owner}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty routes}">
                    <tr class="empty-row">
                        <td colspan="7">Маршрутов пока нет — добавьте первый ниже</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <div class="section-label">Добавить новый маршрут</div>
        <div class="panel wide">
            <form action="${pageContext.request.contextPath}/routes" method="POST">
                <div class="field">
                    <label for="name">Название маршрута</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="field-group">
                    <p class="group-title">Координаты (Y ≤ 71)</p>
                    <div class="field-row">
                        <div class="field"><label for="coordX">X</label><input type="number" step="0.01" id="coordX" name="coordX" required></div>
                        <div class="field"><label for="coordY">Y</label><input type="number" max="71" id="coordY" name="coordY" required></div>
                    </div>
                </div>

                <div class="field-group">
                    <p class="group-title">Точка отправления</p>
                    <div class="field-row">
                        <div class="field"><label for="fromX">X</label><input type="number" id="fromX" name="fromX" required></div>
                        <div class="field"><label for="fromY">Y</label><input type="number" id="fromY" name="fromY" required></div>
                        <div class="field"><label for="fromZ">Z</label><input type="number" step="0.01" id="fromZ" name="fromZ" required></div>
                    </div>
                </div>

                <div class="field-group">
                    <p class="group-title">Точка прибытия</p>
                    <div class="field-row">
                        <div class="field"><label for="toX">X</label><input type="number" id="toX" name="toX" required></div>
                        <div class="field"><label for="toY">Y</label><input type="number" id="toY" name="toY" required></div>
                        <div class="field"><label for="toZ">Z</label><input type="number" step="0.01" id="toZ" name="toZ" required></div>
                    </div>
                </div>

                <div class="field" style="max-width: 220px;">
                    <label for="distance">Дистанция (> 1)</label>
                    <input type="number" step="0.01" min="1.01" id="distance" name="distance" required>
                </div>

                <button type="submit" class="btn" style="margin-top: 8px;">Добавить маршрут</button>
            </form>
        </div>
    </div>
</body>
</html>

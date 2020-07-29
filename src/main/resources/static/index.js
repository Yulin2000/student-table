<tbody>
<tr th:each="student: ${oldStudentList}" th:id="${student.id}" style="display: none">
    <td th:text="${student.id}"></td>
    <td><input type="text" name="name" th:value="${student.name}" size="10" /></td>
    <td><input type="text" name="sex" th:value="${student.sex}" size="2" /></td>
    <td><input type="text" name="course" th:value="${student.course}" size="5" /></td>
    <td><input type="text" name="phone_number" th:value="${student.phone_number}" size="20" /></td>
    <td><input type="text" name="birth" th:value="${student.birth}" size="10" /></td>
    <td><input type="text" name="note" th:value="${student.note}" size="10" /></td>
    <input type="hidden" name="id" th:value="${student.id}"/>
</tr>
</tbody>



<div style="display: none" id="modifyForm">
<blockquote class="layui-elem-quote">在下表更改学生信息</blockquote>
<form action="#" th:action="@{/home}" method="post">
    <table lay-filter="modify_table">
        <thead>
            <tr>
                <th lay-data="{field:'id', sort:true, align:'center'}">学号</th>
                <th lay-data="{field:'name', align:'center', edit:'text'}">姓名</th>
                <th lay-data="{field:'sex', align:'center', edit:'text'}">性别</th>
                <th lay-data="{field:'class', sort:true, align:'center', edit:'text'}">班级</th>
                <th lay-data="{field:'phone', align:'center', edit:'text'}">电话</th>
                <th lay-data="{field:'birth', align:'center', edit:'text'}">生日</th>
                <th lay-data="{field:'note', align:'center', edit:'text'}">备注</th>
            </tr>
        </thead>
    </table>
    <button type="submit" name="button" value="Modify" class="layui-btn">保存</button>
</form>
</div>


                            //name input block
                            var div_item = document.createElement("div");
                            div_item.className = "layui-form-item";
                            var label_name = document.createElement("label");
                            label_name.className = "layui-form-label";
                            label_name.innerHTML = "姓名";
                            div_item.appendChild(label_name);                         //append label element
                            var input_name = document.createElement("input");
                            input_name.setAttribute("type", "text");
                            input_name.setAttribute("name", "name");
                            //input_name.setAttribute("value", checked_students[i].name);
                            input_name.className = "layui-input-block layui-input";
                            div_item.appendChild(input_name);                         //append input element
                            div_main.appendChild(div_item);


                            <!-----------------------------------------Modify Popup Model---------------------------------------------->
    
                            <div style="display: none" id="modifyForm">
                                <blockquote class="layui-elem-quote">在下表更改学生信息</blockquote>
                                <form id="modify_form" style="width: 90%" class="layui-form" action="#" th:action="@{/home}" method="post">
                                    <button id="modify_confirm" type="submit" name="button" value="Modify" class="layui-btn">保存</button>
                                </form>
                            </div>
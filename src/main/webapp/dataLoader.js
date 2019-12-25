let baseUrl = "lab1";

function addCity(callback = _defaultCallback, onError = _defaultOnError, cont = this) { // Добавление на сервер
    let cityName = $("#cityAdd").val();
    let data = {name: cityName};
    $.ajax({
        url: "crud/city",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
        success: callback,
        error: onError,
        content: cont
    });

    // let id = Math.floor(Math.random() * Math.floor(1000));
    // data.id = id;
    // callback(data);
}

function addSchool(cityId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let schoolName = $("#schoolAdd").val();
    let data = {name: schoolName, city_id: cityId};
    $.ajax({
        url: "crud/school",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
        success: callback,
        error: onError,
        content: cont
    });

    // let id = Math.floor(Math.random() * Math.floor(1000));
    // data.id = id;
    // callback(data);
}

function addProfile(schoolId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let profileName = $("#addProfileName").val();
    let profileAge = $("#addProfileAgeUpd").val();
    let profileTypeId = $("#addProfileType").val();
    let classLevel = $("#addClassLevelUpd").val();
    let data = {
        name: profileName,
        age: profileAge,
        profile_type_id: profileTypeId,
        class_level: classLevel,
        school_id: schoolId
    };
    $.ajax({
        url: "crud/profile",
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
        success: callback,
        error: onError,
        content: cont
    });

    // let id = Math.floor(Math.random() * Math.floor(1000));
    // data.id = id;
    // callback(data);
}

function getSchoolsByCityId(cityId, callback = drawCityes, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "school_of_city/" + cityId,
        type: "GET",
        // data: {
        //     id: cityId
        // },
        success: callback,
        error: onError,
        content: cont
    })
    // let data = [{"profilesIds": [2, 4, 1], "id": 1, "name": "Школа №46", "city_id": 1}, {
    //     "profilesIds": [3],
    //     "id": 2,
    //     "name": "Лицей №51",
    //     "city_id": 1
    // }, {"profilesIds": [], "id": 11, "name": "valid_school", "city_id": 1}];
    // callback(data);
}

function getProfilesBySchoolId(schoolId, callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "profiles_of_school/" + schoolId,
        type: "GET",
        // data: {
        //     id: schoolId
        // },
        success: callback,
        error: onError,
        content: cont
    })
    // let data = [{
    //     "id": 2,
    //     "name": "Не Миша",
    //     "age": 99,
    //     "profile_type": {"id": 2, "name": "Учитель"},
    //     "class_level": null,
    //     "school_id": 1
    // }, {
    //     "id": 4,
    //     "name": "valid_prof",
    //     "age": 123,
    //     "profile_type": {"id": 2, "name": "Учитель"},
    //     "class_level": null,
    //     "school_id": 1
    // }, {
    //     "id": 1,
    //     "name": "Миша",
    //     "age": 23,
    //     "profile_type": {"id": 1, "name": "Студент"},
    //     "class_level": "11",
    //     "school_id": 1
    // }];
    // callback(data);
}

function getAllCityes(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "crud/city",
        type: "GET",
        success: callback,
        error: onError,
        content: cont
    })
    // let data = [{"id": 2, "name": "default city 2", "schools_ids": [3, 6]}, {
    //     "id": 11,
    //     "name": "idea_test",
    //     "schools_ids": []
    // }, {"id": 20, "name": "valid_city", "schools_ids": []}, {
    //     "id": 17,
    //     "name": "valid_city",
    //     "schools_ids": [4, 10]
    // }, {"id": 22, "name": "ds_fr_fr", "schools_ids": []}, {
    //     "id": 23,
    //     "name": "Руссконазванный",
    //     "schools_ids": []
    // }, {"id": 1, "name": "default city 1 upd", "schools_ids": [1, 2, 11]}];
    // callback(data);
}

function getAllProfiles(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "crud/profile",
        type: "GET",
        success: callback,
        error: onError,
        content: cont
    })
    // let data = [{
    //     "id": 2,
    //     "name": "Не Миша",
    //     "age": 99,
    //     "profile_type": {"id": 2, "name": "Учитель"},
    //     "class_level": null,
    //     "school_id": 1
    // }, {
    //     "id": 3,
    //     "name": "Таня",
    //     "age": 22,
    //     "profile_type": {"id": 1, "name": "Студент"},
    //     "class_level": "11",
    //     "school_id": 2
    // }, {
    //     "id": 4,
    //     "name": "valid_prof",
    //     "age": 123,
    //     "profile_type": {"id": 2, "name": "Учитель"},
    //     "class_level": null,
    //     "school_id": 1
    // }, {
    //     "id": 1,
    //     "name": "Миша",
    //     "age": 23,
    //     "profile_type": {"id": 1, "name": "Студент"},
    //     "class_level": "11",
    //     "school_id": 1
    // }];
    // callback(data);
}

function getAllProfileTypes(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    $.ajax({
        url: "crud/profile_type",
        type: "GET",
        success: callback,
        error: onError,
        content: cont
    })
    // let data = [{"id": 1, "name": "Студент"}, {"id": 2, "name": "Учитель"}];
    // callback(data);
}

function sendEditedSchoolToServer(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let dialog = $("#updateSchoolDialog");
    let id = dialog.attr("e_id");
    let cityId = dialog.attr("p_id");
    let name = $("#schoolNameUpd").val();
    let data = {id: id, name: name, city_id: cityId};
    // let $this = $('body').find('.schoolName[data-id="'+ id +'"]');
    // let content = $this.html();
    $.ajax({
            url: "crud/school",
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
            success: callback,
            error: onError,
        }
    );
    // console.log(data);
}

function sendEditedCityToServer(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let id = $("#updateCityDialog").attr("e_id");
    let name = $("#cityNameUpd").val();
    let data = {id: id, name: name};
    $.ajax({
            url: "crud/city",
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
            success: callback,
            error: onError,
        }
    );
}

function sendEditedProfileToServer(callback = _defaultCallback, onError = _defaultOnError, cont = this) {
    let dialog = $("#updateProfileDialog");
    let id = dialog.attr("e_id");
    let sch_id = dialog.attr("p_id");
    let name = $("#profileNameUpd").val();
    let age = $("#profileAgeUpd").val();
    let typeId = $("#profileTypeUpd").val();
    let classLevel = $("#classLevelUpd").val();
    let data = {id: id, name: name, age: age, profile_type_id: typeId, class_level: classLevel, school_id: sch_id};
    $.ajax({
            url: "crud/profile",
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),
            success: callback,
            error: onError,
        }
    );
    console.log(data);
}

function sendDeleteCity(id) { // удаление города снизу тож самое для студента и школы
    // let $this = $('body').find('.cityName[data-id="'+ id +'"]');
    // let content = $this.html();
    let cell = $("#ct" + id);
    $.ajax({
        url: "crud/city/" + id,
        type: "DELETE",
        // data: {
        //     id: id
        // },
        success: () => {
            // $this.closest('cell').remove();
            cell.remove();
        },
    })
    // alert(elem)
}


function sendDeleteSchool(id) {
    // let $this = $('body').find('.schoolName[data-id="'+ id +'"]');
    // let content = $this.html();
    let cell = $("#schl" + id);
    $.ajax({
        url: "crud/school/" + id,
        type: "DELETE",
        // data: {
        //     id: id
        // },
        success: function () {
            // $this.closest('.cell').remove();
            cell.remove()
        },
    });
}

function sendDeleteStudent(id) {
    // let $this = $('body').find('.studentName[data-id="'+ id +'"]');
    // let content = $this.html();
    let cell = $("#stdnt" + id);
    $.ajax({
        url: "crud/profile/" + id,
        type: "DELETE",
        // data: {
        //     id: id
        // },
        success: function () {
            // $this.closest('.cell').remove();
            cell.remove()
        },
    });
}

function _defaultCallback(data) {
    console.log(data)
}

function _defaultOnError(jqXHR, textStatus, errorThrown) {
    let errorObj = JSON.parse(jqXHR.responseText);
    alert(errorObj.message);
}
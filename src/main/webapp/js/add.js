

$(function () {
    $.extend($.fn.validatebox.defaults.rules, {
        length: {
            validator: function (value) {
                if (value.length <= 10) {
                    return true;
                } else {
                    return false;
                }
            },
            message: '长度溢出'
        }
    });
});



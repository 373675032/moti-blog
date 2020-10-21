/* 
 Created on : Jul 17, 2017, 12:31:12 AM
 Author     : Atta-Ur-Rehman Shah (http://attacomsian.com)
 */
$(function () {
    //default date range picker
    $('#daterange').daterangepicker({
        autoApply:true
    });

    //date time picker
    $('#datetime').daterangepicker({
        timePicker: true,
        timePickerIncrement: 30,
        locale: {
            format: 'MM/DD/YYYY h:mm A'
        }
    });

    //single date
    $('#date').daterangepicker({
        singleDatePicker: true,
    });
});
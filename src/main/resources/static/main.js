var globalNode;

initTree();

//初始化树结构
function initTree() {
    var arr = [{"chapterNum":1,"partTitle":"零基础 DAY 1","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/buy.jpg"},{"chapterNum":2,"partTitle":"零基础 DAY 2","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/wantToBuy.jpg"},{"chapterNum":3,"partTitle":"微基础 DAY 1","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/buy.jpg"},{"chapterNum":4,"partTitle":"零基础 试听课1","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/buy.jpg"},{"chapterNum":5,"partTitle":"零基础 试听课2","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":6,"partTitle":"零基础 试听课3","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/hotel.jpg"},{"chapterNum":7,"partTitle":"微基础 试听课1","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/guide.jpg"},{"chapterNum":8,"partTitle":"微基础 试听课2","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/searchRoad.jpg"},{"chapterNum":9,"partTitle":"微基础 试听课3","courseNum":5,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/hotel.jpg"},{"chapterNum":10,"partTitle":"微基础 DAY 2","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/buyConsult.jpg"},{"chapterNum":11,"partTitle":"零基础 DAY 3","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/chooseProduct.jpg"},{"chapterNum":12,"partTitle":"零基础 DAY 4","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/productSize.jpg"},{"chapterNum":13,"partTitle":"零基础 DAY 5","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":14,"partTitle":"零基础 DAY 6","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/tryCloth.jpg"},{"chapterNum":15,"partTitle":"零基础 DAY 7","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/the-loop-personal-shopper.jpg"},{"chapterNum":16,"partTitle":"零基础 DAY 8","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/card.jpg"},{"chapterNum":17,"partTitle":"零基础 DAY 9","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/tax.jpeg"},{"chapterNum":18,"partTitle":"零基础 DAY 10","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":19,"partTitle":"零基础 DAY 11","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/Reservation.jpg"},{"chapterNum":20,"partTitle":"零基础 DAY 12","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/Day12.jpg"},{"chapterNum":21,"partTitle":"微基础 DAY 3","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/choose.jpg"},{"chapterNum":22,"partTitle":"零基础 DAY 13","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/image/bookTicket.jpg"},{"chapterNum":23,"partTitle":"微基础 DAY 4","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/dollar.jpg"},{"chapterNum":24,"partTitle":"微基础 DAY 5","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":25,"partTitle":"零基础 DAY 14","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/image/bookTicket.jpg"},{"chapterNum":26,"partTitle":"零基础 DAY 15","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":27,"partTitle":"微基础 DAY 6","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/Fitting%20room.jpg"},{"chapterNum":28,"partTitle":"微基础 DAY 7","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/card.jpg"},{"chapterNum":29,"partTitle":"零基础 DAY 16","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/flight.jpg"},{"chapterNum":30,"partTitle":"微基础 DAY 8","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/cashmere.jpg"},{"chapterNum":31,"partTitle":"零基础 DAY 17","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/delay.jpg"},{"chapterNum":32,"partTitle":"微基础 DAY 9","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/tax.jpeg"},{"chapterNum":33,"partTitle":"零基础 DAY 18","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/flight_takeoff-wide.jpg"},{"chapterNum":34,"partTitle":"微基础 DAY 10","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":35,"partTitle":"零基础 DAY 19","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/package.jpg"},{"chapterNum":36,"partTitle":"微基础 DAY 11","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/Reservation.jpg"},{"chapterNum":37,"partTitle":"零基础 DAY 20","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":38,"partTitle":"零基础 DAY 21","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":39,"partTitle":"微基础 DAY 12","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/day12.jpg"},{"chapterNum":40,"partTitle":"微基础 DAY 13","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/tuoyun.jpg"},{"chapterNum":41,"partTitle":"微基础 DAY 14","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/delay.jpg"},{"chapterNum":42,"partTitle":"微基础 DAY 15","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":43,"partTitle":"零基础 DAY 22","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":44,"partTitle":"零基础 DAY 23","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":45,"partTitle":"微基础 DAY 16","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":46,"partTitle":"零基础 DAY 24","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":47,"partTitle":"微基础 DAY 17","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/airplane.jpg"},{"chapterNum":48,"partTitle":"零基础 DAY 25","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/security.jpg"},{"chapterNum":49,"partTitle":"微基础 DAY 18","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/boarding.jpg"},{"chapterNum":50,"partTitle":"零基础 DAY 26","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/airplane.jpg"},{"chapterNum":51,"partTitle":"微基础 DAY 19 ","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/uncomfortable.jpg"},{"chapterNum":52,"partTitle":"微基础 DAY 20","courseNum":4,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/review.jpg"},{"chapterNum":53,"partTitle":"零基础 DAY 27","courseNum":3,"courseImg":"http://justtalk.oss-cn-shanghai.aliyuncs.com/picture/airplane.jpg"}];
    $.ajax({
        url: "/queryAllChapterInfo",
        success: function(res){
            var arr = JSON.parse(res);
            console.log(arr);
            arr.forEach(function (element, index, arr){
                element["text"] = element["chapterTitle"];
            });

            var zeroArr = arr.filter(function(obj){
                return obj.courseNum == 3
            });
            var tinyArr = arr.filter(function(obj){
                return obj.courseNum == 4
            });
            var freeArr = arr.filter(function(obj){
                return obj.courseNum == 5
            });

            var tree = [
                {
                    text: "零基础课程",
                    courseNum: 3,
                    nodes: zeroArr
                },{
                    text: "微基础课程",
                    courseNum: 4,
                    nodes: tinyArr
                },{
                    text: "试听课程",
                    courseNum: 5,
                    nodes: freeArr
                }
            ];

            $('#tree').treeview({
                data: tree,
                showTags: true
            });

            //折叠整个树
            $('#tree').treeview('collapseAll');

            //绑定点击事件
            $('#tree').on('nodeSelected',function(event,node) {
                console.log(node);
                //chapter层点击,根据chapterNum查询part
                if(node.level == 2){
                    var tree = $('#tree');
                    var selectedNode = tree.treeview('findNodes', ['^' + node.chapterNum + '$', 'chapterNum']);
                    if(selectedNode.length == 1){
                        $.ajax({
                            url: "/queryPartInfoByChapterNum",
                            data: {chapterNum: node.chapterNum},
                            success: function (res) {
                                var partArr = JSON.parse(res);
                                partArr.forEach(function (element, index, arr){
                                    element["text"] = element["partTitle"];
                                    tree.treeview('addNode', [element, selectedNode, , { silent: true }]);
                                });
                            }
                        })
                    }
                }else if(node.level == 3){
                    var tree = $('#tree');
                    var selectedNode = tree.treeview('findNodes', ['^' + node.partNum + '$', 'partNum']);
                    if(selectedNode.length == 1){
                        $.ajax({
                            url: "/queryFileInfoByPartNum",
                            data: {partNum: node.partNum},
                            success: function (res) {
                                var fileArr = JSON.parse(res);
                                fileArr.forEach(function (element, index, arr){
                                    element["text"] = element["audioPath"];
                                    tree.treeview('addNode', [element, selectedNode, , { silent: true }]);
                                });
                            }
                        })
                    }
                }
            });
        }
    })
}


//绑定添加节点事件
$("#add-btn").click(function(){
    var selectedNode = $('#tree').treeview('getSelected');
    globalNode = selectedNode;
    if(selectedNode && selectedNode[0] && selectedNode[0].level != 4){
        var level = selectedNode[0].level;
    }else{
        return;
    }
    if(level == 1){
        var courseNum = selectedNode[0].courseNum;
        $("#courseNum").val(courseNum);
        $('#addChapter').modal();
    }else if(level == 2){
        var chapterNum = selectedNode[0].chapterNum;
        $("#chapterNum").val(chapterNum);
        $('#addPart').modal();
    }else if(level == 3){
        var partNum = selectedNode[0].partNum;
        $("#partNum").val(partNum);
        $('#addFile').modal();
    }

        // $('#tree').treeview('addNode', [{
        //     text: "CNM"
        // }, selectedNode, , { silent: true }]);


})

//绑定删除节点事件
$("#remove-btn").click(function(){
    var selectedNode = $('#tree').treeview('getSelected');
    if(selectedNode && (selectedNode[0].level !== 1)){
        $('#tree').treeview('removeNode', [ selectedNode, { silent: true } ]);
    }
})

//添加chapter
$("#chapterSave").click(function(){
    var chapterTitle = $("#chapterTitle").val();
    var courseImg = $("#courseImg").val();
    var courseNum = $("#courseNum").val();
    var data = {
        chapterTitle: chapterTitle,
        courseNum: courseNum,
        courseImg: courseImg
    }
    $.ajax({
        url: "/addNewChapter",
        data: data,
        success: function (res) {
            $('#tree').treeview('addNode', [{
                text: chapterTitle,
                chapterTitle: chapterTitle,
                courseNum: courseNum,
                courseImg: courseImg,
                chapterNum: res
            }, globalNode, , { silent: true }]);
        }
    })
    $('#addChapter').modal("hide");
});

//添加part
$("#partSave").click(function(){
    var partTitle = $("#partTitle").val();
    var chapterNum = $("#chapterNum").val();
    var data = {
        partTitle: partTitle,
        chapterNum: chapterNum
    }
    $.ajax({
        url: "/addNewParts",
        data: {
            paraJson: JSON.stringify([data])
        },
        success: function (res) {
            $('#tree').treeview('addNode', [{
                text: partTitle,
                partTitle: partTitle,
                chapterNum: chapterNum,
                partNum: res
            }, globalNode, , { silent: true }]);
        }
    })
    $('#addPart').modal("hide");
});

//添加file
$("#fileSave").click(function(){
    var partNum = $("#partNum").val();
    var fileType = $('#fileType input:radio:checked').val();
    var filePath = $("#filePath").val();
    var fileName = $("#fileName").val();
    var audioPath = $("#audioPath").val();
    var data = {
        fileType: fileType,
        filePath: filePath,
        fileName: fileName,
        audioPath: audioPath,
        partNum: partNum
    }
    $.ajax({
        url: "/addNewFiles",
        data: {
            paraJson: JSON.stringify([data])
        },
        success: function (res) {
            $('#tree').treeview('addNode', [{
                text: audioPath,
                fileType: fileType,
                filePath: filePath,
                fileName: fileName,
                audioPath: audioPath,
                partNum: partNum,
                fileNum: res
            }, globalNode, , { silent: true }]);
        }
    })
    $('#addFile').modal("hide");
});
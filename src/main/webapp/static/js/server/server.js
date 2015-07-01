var tip = {'tomcat':'格式：http://121.14.23.177:8080/gsinfo',
			'oracle':'格式：jdbc:oracle:thin:@192.168.11.11:1521/orcl',
			'hadoop':'格式：http://121.14.23.177:50070',
			'solr':'格式：http://121.14.23.177:9999'};

function urlTip() {
	for(var i=0; i<$(':radio').length; i++) {
		var id = $(':radio')[i].value;
		var rd = $('#'+id);
		if($(':radio')[i].checked) {
			$('#url').attr('placeholder', tip[id]);
		}
		rd.change(function() {
			$('#url').attr('placeholder', tip[this.value]);
		});
	}
}

urlTip();
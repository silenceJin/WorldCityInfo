window.onload = function() {
	new Vue({
		el: "#city",
		data: {
			paging: {},
			countryCode: '',
			cityName: ''
		},
		methods: {
			findCiryByCountryCode: function(pageNum) {
				var countryCode = encodeURIComponent(this.countryCode);
				var cityName = encodeURIComponent(this.cityName);
				this.$http.get(`/city/findCity/${pageNum}?countryCode=${countryCode}&cityName=${cityName}`).then(
					function(result) {
						var res = result.body;
						if (res.status == 200) {
							this.paging = res.data;
						} else {
							alert(`${res.status} : ${res.message}`);
						}
					});
			}
		},
		created() {
			this.countryCode = GetQueryString("countryCode");
			this.findCiryByCountryCode(1);
		}
	});

	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var par = window.location.search;
		if (par != null && par != '' && par.length > 1) {
			var r = par.substr(1).match(reg);
			if (r != null) return unescape(r[2]);
		}
		return '';
	}

}

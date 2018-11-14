/**
 * 
 */
window.onload = function() {
	new Vue({
		el: "#country",
		data: {
			paging: {},
			countryName: ''
		},
		methods: {
			findCountry: function(pageNum) {
				this.$http.get(`/country/allCountry/${pageNum}?countryName=${encodeURIComponent(this.countryName)}`).then(
					function(result) {
						var res = result.body;
						if (res.status == 200) {
							this.paging = res.data;
						} else {
							alert(`${res.status} : ${res.message}`);
						}
					},
					function(err) {
						console.log(err);
					});
			},
			findCity(countryCode){
				window.location.href = `city.html?countryCode=${countryCode}`;
			}
		},
		created() {
			this.findCountry(1);
		}
	});
}

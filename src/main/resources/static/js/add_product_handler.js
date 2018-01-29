var AddProductHandler = function(http) {
    return {
        addProduct: function(id) {
            var url = '/add-to-basket/__id__'.replace("__id__", id);
            axios.post(url)
              .then(function (response) {
                document.dispatchEvent(new Event('item_placed_in_basket'));
              })
              .catch(function (error) {
                console.log("it is not possible to add product, check error message");
              });
        }
    }
}
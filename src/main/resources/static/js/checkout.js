var CheckoutComponent = function() {

    $('#checkout').on('click', '.confirm-checkout', function(e) {
        axios.post("/checkout", {
            email: $('.delivery-details input[name="email"]').val()
        })
            .then(function(response){
                window.location.replace(response.data);
            })
            .catch(function(error){
                console.log(error);
            })
        ;
    });
    return {
        initialize: function() {
            $('#checkout').modal('show');
            $('#checkout form').trigger('reset');
        }
    }
}


var checkout = new CheckoutComponent();

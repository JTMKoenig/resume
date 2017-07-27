<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add New Power</h4>
            </div>
            <div class="modal-body">
                <form method="POST" action="${pageContext.request.contextPath}/power/add" class="col-md-12">
                    <div class="form-group">
                        <label for="powerType">Power Type:</label>
                        <input type="text" class="form-control" name="powerType"
                               placeholder="Power Type" required/>
                    </div>
                    <div class="form-group">
                        <label for="powerDescription">Description:</label>
                        <textarea class="form-control" name="powerDescription" required></textarea>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary">Add Power</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
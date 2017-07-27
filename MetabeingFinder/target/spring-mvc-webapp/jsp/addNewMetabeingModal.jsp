<div class="modal fade" id="metaModal" tabindex="-2" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Add New Metabeing</a></li>
                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Add New Power</a></li>
                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Add New Organization</a></li>
                    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <div class="modal-body">
                            <form method="POST" action="${pageContext.request.contextPath}/power/add" class="col-md-12">
                                <div class="form-group">
                                    <label for="name">Name:</label>
                                    <input type="text" class="form-control" name="name"
                                           placeholder="Name" required/>
                                </div>

                                <div class="form-group">
                                    <label for="powers">Powers:</label>
                                    <select class="form-control" name="powerId">
                                        <option value="selectPowers"
                                                selected hidden >
                                            Select Power</option>
                                        <c:forEach items="${allPowers}" var="power">
                                            <option value="${power.powerId}">${power.powerType}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <a data-toggle="modal" data-target="#myModal">
                                        +add new power |
                                    </a>
                                    <a class="add-new-power">+add more powers</a>
                                </div>
                                <div class="form-group">
                                    <label for="organization">Organization:</label>
                                    <select class="form-control" name="organizationId">
                                        <option value="selectOrganization"
                                                selected hidden >
                                            Select Organization</option>
                                        <c:forEach items="${allOrgs}" var="org">
                                            <option value="${org.organizationId}">${org.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <a class="add-new-power">+add new to list |</a>
                                    <a class="add-new-power">+add more to Metabeing</a>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description:</label>
                                    <textarea class="form-control" name="description"></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="alias">Alias:</label>
                                    <input type="text" class="form-control"
                                           placeholder="Alias" name="alias"/>
                                </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Add Power</button>
                        </div>
                        </form>
                    </div>
                    <div role="tabpanel" class="tab-pane" id="profile">
                        <div class="modal-body">
                            <form method="POST" action="${pageContext.request.contextPath}/power/add/fromSighting" class="col-md-12">
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

                    </div>
                    <div role="tabpanel" class="tab-pane" id="messages">message</div>
                    <div role="tabpanel" class="tab-pane" id="settings">setting</div>
                </div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add New Metabeing</h4>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
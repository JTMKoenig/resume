<%-- 
    Document   : dvdForm
    Created on : Jul 5, 2017, 7:23:49 PM
    Author     : jono
--%>

      
                        <div class="form-header">
                        
                        </div>
                        <hr align="left" width="50%"/>

                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" name="title"
                                   placeholder="Title" style="width: 50%" required/>
                        </div>

                        <div class="form-group">
                            <label for="year">Release Year:</label>
                            <input type="text" pattern="[0-9]{4}" oninvalid="invalidMsg(this)" oninput="invalidMsg(this)" class="form-control"
                                   placeholder="Release Year" name="releaseDate" style="width: 50%" />
                        </div>

                        <div class="form-group">
                            <label for="director">Director:</label>
                            <input type="text" class="form-control"
                                   placeholder="Director" name="director" style="width: 50%"/>
                        </div>

                        <div class="form-group">
                            <label for="rating">MPAA Rating:</label>
                            <select class="form-control" name="rating" style="width: 20%">
                                <option value="chooseRating"
                                        selected hidden >
                                    Choose Rating</option>
                                <option value="G">G</option>
                                <option value="PG">PG</option>
                                <option value="PG-13">PG-13</option>
                                <option value="R">R</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="note">Notes:</label>
                            <textarea class="form-control" name="notes"
                                      style="width: 50%"></textarea>
                        </div>

                        

                           



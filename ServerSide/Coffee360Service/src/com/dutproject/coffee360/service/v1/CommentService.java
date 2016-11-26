package com.dutproject.coffee360.service.v1;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.dutproject.coffee360.model.bean.Comment;
import com.dutproject.coffee360.model.bean.NewComment;
import com.dutproject.coffee360.model.bean.PlaceReport;
import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bo.CommentBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;

@Path("/v1/comment")
public class CommentService extends BaseService {

    private CommentBO commentBO = new CommentBO();
    
    @GET
    @Path("/place/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(
            @QueryParam("placeId") int placeId,
            @QueryParam("fromIndex") int fromIndex,
            @QueryParam("toIndex") int toIndex) {
        List<Comment> comments;
        try {
            comments = commentBO.getComments(placeId, fromIndex, toIndex);
            GenericEntity<List<Comment>> entity = new GenericEntity<List<Comment>>(comments) {};
            return Response.status(200).entity(entity).build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    
    @POST
    @Path("/place/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({Role.ROLE_USER})
    public Response addComment(NewComment comment, @Context SecurityContext securityContext) throws SQLException {
        int accountId = getAccountId(securityContext);
        String result = commentBO.addComment(accountId, comment);
        PrimitiveType<String> stringType = new PrimitiveType<>();
        stringType.setValue(result);
        return Response.status(200).entity(stringType).build();
    }
    
}

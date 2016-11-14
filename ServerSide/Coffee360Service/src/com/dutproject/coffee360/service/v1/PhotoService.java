package com.dutproject.coffee360.service.v1;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.bo.PhotoBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;

@Path("/v1/photo")
public class PhotoService extends BaseService {
	private PhotoBO reportBO = new PhotoBO();

	@POST
	@Path("/place/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Secured({ Role.ROLE_USER })
	public Response uploadPhoto(@FormDataParam("photo") InputStream in,
			@FormDataParam("photo") FormDataContentDisposition detail,
			@QueryParam("placeId") int placeId,
			@Context SecurityContext securityContext) {
		int accountId = getAccountId(securityContext);
		UploadedPhoto uploadedPhoto = reportBO.uploadPlacePhoto(accountId, in, detail.getFileName(), placeId);
		return Response.status(200).entity(uploadedPhoto).build();
	}
}

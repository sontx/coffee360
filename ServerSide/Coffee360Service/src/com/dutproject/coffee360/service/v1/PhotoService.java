package com.dutproject.coffee360.service.v1;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.bo.PhotoBO;

@Path("/v1/photo")
public class PhotoService {
	private PhotoBO reportBO = new PhotoBO();

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadPhoto(@FormDataParam("photo") InputStream in,
			@FormDataParam("photo") FormDataContentDisposition detail) {
		Account account = new Account();
		account.setId(1);		
		UploadedPhoto uploadedPhoto = reportBO.uploadPhoto(account, in, detail.getFileName());
		return Response.status(200).entity(uploadedPhoto).build();
	}
}

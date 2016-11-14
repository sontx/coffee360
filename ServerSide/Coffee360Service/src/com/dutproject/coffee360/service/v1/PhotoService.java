package com.dutproject.coffee360.service.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dutproject.coffee360.model.bean.UploadedPhoto;
import com.dutproject.coffee360.model.bo.PhotoBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;

@Path("/v1/photo")
public class PhotoService extends BaseService {
	private PhotoBO photoBO = new PhotoBO();

	@POST
	@Path("/place/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Secured({ Role.ROLE_USER })
	public Response uploadPlacePhoto(@FormDataParam("photo") InputStream in,
			@FormDataParam("photo") FormDataContentDisposition detail, @QueryParam("placeId") int placeId,
			@Context SecurityContext securityContext) {
		int accountId = getAccountId(securityContext);
		UploadedPhoto uploadedPhoto = photoBO.uploadPlacePhoto(accountId, in, detail.getFileName(), placeId);
		return Response.status(200).entity(uploadedPhoto).build();
	}

	@GET
	@Path("{id}")
	@Produces({ "image/jpeg", "image/png" })
	public StreamingOutput dowloadPhoto(@PathParam("id") int id) {
		try {
			InputStream in = photoBO.getImageInputStream(id);
			return new ImageStreamingOutput(in);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static class ImageStreamingOutput implements StreamingOutput {
		private final InputStream in;

		private ImageStreamingOutput(InputStream in) {
			this.in = in;
		}

		@Override
		public void write(OutputStream out) throws IOException, WebApplicationException {
			byte[] buffer = new byte[1024];
			int chunk = 0;
			while ((chunk = in.read(buffer, 0, buffer.length)) > 0) {
				out.write(buffer, 0, chunk);
			}
		}
	}
}

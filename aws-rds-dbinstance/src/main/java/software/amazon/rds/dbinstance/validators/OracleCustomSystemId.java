package software.amazon.rds.dbinstance.validators;

import software.amazon.rds.common.request.RequestValidationException;
import software.amazon.rds.dbinstance.ResourceModel;
import software.amazon.rds.dbinstance.util.ResourceModelHelper;

public class OracleCustomSystemId {
    public static void validateRequest(final ResourceModel model) throws RequestValidationException {
        if (ResourceModelHelper.isRestoreToPointInTime(model) && model.getDBSystemId() != null) {
            throw new RequestValidationException("Cannot set DBSystemId when restoring to point in time");
        }

        if (ResourceModelHelper.isDBInstanceReadReplica(model) && model.getDBSystemId() != null) {
            throw new RequestValidationException("Cannot set DBSystemId when creating read replica");
        }

        if (ResourceModelHelper.isRestoreFromSnapshot(model) && model.getDBSystemId() != null) {
            throw new RequestValidationException("Cannot set DBSystemId when restoring from snapshot");
        }
    }
}
